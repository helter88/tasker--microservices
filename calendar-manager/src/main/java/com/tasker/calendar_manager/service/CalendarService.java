package com.tasker.calendar_manager.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.tasker.calendar_manager.exception.CreationEventFailed;
import com.tasker.calendar_manager.service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final GoogleCalendarService googleCalendarService;
    public Event saveEvent(EventDto eventDto) throws IOException, GeneralSecurityException {
        var event = googleCalendarService.createEvent(eventDto.id(), eventDto.task(), eventDto.date(), eventDto.description());
        if(event.getId().isBlank()){
            throw new CreationEventFailed("Couldn't create event in Google Calendar");
        }
        return event;
    }

    public List<Event> getAllEvents(DateTime fromDate) throws GeneralSecurityException, IOException {
        return googleCalendarService.getAllEvents(fromDate);
    }

    public Event updateEvent(EventDto eventDto) throws GeneralSecurityException, IOException {
        return googleCalendarService.updateEvent(eventDto.id(), eventDto.task(), eventDto.date(), eventDto.description());
    }
}
