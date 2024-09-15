package com.tasker.calendar_manager.service;

import com.tasker.calendar_manager.service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final GoogleCalendarService googleCalendarService;
    public void saveEvent(EventDto eventDto) throws IOException {
        googleCalendarService.createEvent(eventDto.task(), eventDto.date());
    }
}
