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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class GoogleCalendarService {
    private final GoogleCredentialService calendarService;

    public String createEvent(String title, Instant startTime) throws IOException, GeneralSecurityException {
        Event event = new Event()
                .setSummary(title);

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
        event = calendarService.setupCredential().events().insert(calendarId, event).execute();
        return event.getHtmlLink();
    }
}
