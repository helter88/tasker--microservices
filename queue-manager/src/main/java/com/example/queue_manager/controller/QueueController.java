package com.example.queue_manager.controller;

import com.example.queue_manager.exception.TimerAnnotation;
import com.example.queue_manager.model.Queue;
import com.example.queue_manager.repository.QueueRepository;
import com.example.queue_manager.service.QueueProducer;
import com.example.queue_manager.service.dto.CalendarEventDto;
import com.example.queue_manager.service.utile.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/queue")
@RequiredArgsConstructor
public class QueueController {

    private final QueueProducer queueProducer;

    @PostMapping("/calendar")
    @TimerAnnotation
    public ResponseEntity<?> addNewCalendarEvent(@RequestBody CalendarEventDto calendarEventDto) throws JsonProcessingException {
        queueProducer.createCalendarEventQueue(calendarEventDto);
        return ResponseEntity.ok("Calendar event created");
    }
    @PutMapping("/calendar")
    @TimerAnnotation
    public ResponseEntity<?> updateCalendarEvent(@RequestBody CalendarEventDto calendarEventDto) throws JsonProcessingException {
        queueProducer.updateCalendarEventQueue(calendarEventDto);
        return ResponseEntity.ok("Calendar event updated");
    }

    @PostMapping("/calendar/refresh")
    @TimerAnnotation
    public ResponseEntity<?> refreshCalendarEvent(@RequestBody CalendarEventDto calendarEventDto) throws JsonProcessingException {
        queueProducer.refreshCalendarEventQueue(calendarEventDto);
        return ResponseEntity.ok("Refresh calendar event created");
    }

}
