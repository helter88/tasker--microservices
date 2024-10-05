package com.tasker.calendar_manager.service;

import com.tasker.calendar_manager.exception.CreationEventFailed;
import com.tasker.calendar_manager.service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final GoogleCalendarService googleCalendarService;
    public void saveEvent(EventDto eventDto) throws IOException, GeneralSecurityException {
        var event = googleCalendarService.createEvent(eventDto.task(), eventDto.date());
        if(event.getId().isBlank()){
            throw new CreationEventFailed("Couldn't create event in Google Calendar");
        }
    }
}
