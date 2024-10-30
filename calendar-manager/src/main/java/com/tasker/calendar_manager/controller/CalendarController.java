package com.tasker.calendar_manager.controller;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.tasker.calendar_manager.service.CalendarService;
import com.tasker.calendar_manager.service.GoogleCredentialService;
import com.tasker.calendar_manager.service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("/event")
    public ResponseEntity<ResponseEventDto> saveEvent(@RequestBody EventDto eventDto) throws GeneralSecurityException, IOException {
            Event event = calendarService.saveEvent(eventDto);
            return ResponseEntity.ok(new ResponseEventDto(event.getSummary(), event.getId()));
    }

    @GetMapping("/event")
    public List<Event> getEvents(DateTime fromDate) throws GeneralSecurityException, IOException {
        return calendarService.getAllEvents(fromDate);
    }

    @PutMapping("/event")
    public ResponseEntity<String> updateEvent(@RequestBody EventDto eventDto) throws GeneralSecurityException, IOException {
        Event event = calendarService.updateEvent(eventDto);
        return ResponseEntity.ok("Successfully updated event: " + event.getId());
    }
}
