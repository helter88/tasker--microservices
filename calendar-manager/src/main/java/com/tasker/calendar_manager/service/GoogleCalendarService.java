package com.tasker.calendar_manager.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.tasker.calendar_manager.client.CalendarEventDto;
import com.tasker.calendar_manager.client.QueueManagerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleCalendarService {
    private final GoogleCredentialService googleCredentialService;
    private final QueueManagerClient queueManagerClient;

    public Event createEvent(String id, String title, Instant startTime, String description) throws IOException, GeneralSecurityException {
        Event event = new Event()
                .setICalUID(id)
                .setSummary(title);
        if(description != null){
            event.setDescription(description);
        }
        EventDateTime startDateTime = new EventDateTime()
                .setDateTime(new DateTime(startTime.toEpochMilli()))
                .setTimeZone("UTC");
        event.setStart(startDateTime);

        // Ustawiamy czas zakończenia na godzinę po czasie rozpoczęcia
        Instant endTime = startTime.plus(1, ChronoUnit.HOURS);
        EventDateTime endDateTime = new EventDateTime()
                .setDateTime(new DateTime(endTime.toEpochMilli()))
                .setTimeZone("UTC");
        event.setEnd(endDateTime);

//        W Google Calendar API, każdy użytkownik może mieć wiele kalendarzy.
//                "primary" to specjalny identyfikator, który zawsze odnosi się do głównego kalendarza użytkownika
        String calendarId = "primary";
        event = googleCredentialService.setupCredential().events().insert(calendarId, event).execute();
        queueManagerClient.refreshEvent(new CalendarEventDto(event.getICalUID(), startTime, event.getSummary(),event.getDescription()));
        return event;
    }

    public List<Event> getAllEvents(DateTime fromDate) throws IOException, GeneralSecurityException {
        String calendarId = "primary";
        Events events = googleCredentialService.setupCredential().events().list(calendarId)
                .setTimeMin(fromDate)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events.getItems();
    }

    public Event updateEvent(String iCalUid, String title, Instant startTime, String description) throws GeneralSecurityException, IOException {
        String calendarId = "primary";

        Event existingEvent = null;
        // Pobierz istniejący event
        Events events = googleCredentialService.setupCredential().events().list(calendarId)
                .setICalUID(iCalUid)
                .execute();
        if (events.getItems() != null && !events.getItems().isEmpty()) {
            existingEvent = events.getItems().get(0);
        }
        if (existingEvent != null) {
            // Aktualizuj podstawowe dane
            existingEvent.setSummary(title);

            // Aktualizuj opis tylko jeśli został podany
            if (description != null) {
                existingEvent.setDescription(description);
            }

            // Aktualizuj czas rozpoczęcia
            EventDateTime startDateTime = new EventDateTime()
                    .setDateTime(new DateTime(startTime.toEpochMilli()))
                    .setTimeZone("UTC");
            existingEvent.setStart(startDateTime);

            // Ustaw czas zakończenia na godzinę po czasie rozpoczęcia (zgodnie z logiką z createEvent)
            Instant endTime = startTime.plus(1, ChronoUnit.HOURS);
            EventDateTime endDateTime = new EventDateTime()
                    .setDateTime(new DateTime(endTime.toEpochMilli()))
                    .setTimeZone("UTC");
            existingEvent.setEnd(endDateTime);

            // Wykonaj aktualizację i zwróć zaktualizowany event
            var event = googleCredentialService.setupCredential().events()
                    .update(calendarId, existingEvent.getId(), existingEvent)
                    .execute();
            queueManagerClient.refreshEvent(new CalendarEventDto(event.getICalUID(), startTime, event.getSummary(),event.getDescription()));
            return event;
        }

        throw new IllegalArgumentException("Event with id: " + iCalUid + " not found");
    }
}
