package com.tasker.calandar_manager_entrance.controller;

import com.tasker.calandar_manager_entrance.dto.CalendarEventDto;
import com.tasker.calandar_manager_entrance.service.CalendarManagerEntranceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calendar-entrance")
@RequiredArgsConstructor
@Log4j2
public class CalendarManagerEntranceController {
    private final CalendarManagerEntranceService entranceService;
    @PostMapping
    public ResponseEntity<?> saveCalendarEvent(@RequestBody CalendarEventDto eventDto){
        entranceService.saveCalendarEvent(eventDto);
        return ResponseEntity.ok("Success saved");
    }
    @PutMapping
    public ResponseEntity<?> updateCalendarEvent(@RequestBody CalendarEventDto eventDto){
        entranceService.updateCalendarEvent(eventDto);
        return ResponseEntity.ok("Success updated");
    }

    @PutMapping("/refresh")
    public ResponseEntity<?> refreshCalendarEvent(@RequestBody CalendarEventDto eventDto){
        entranceService.refreshCalendarEvent(eventDto);
        log.info("Event: " + eventDto.id() + "refreshed" );
        return ResponseEntity.ok("Success refreshed");
    }
}
