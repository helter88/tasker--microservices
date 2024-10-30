package com.example.queue_manager.client;

import com.example.queue_manager.service.dto.CalendarEventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "calendar-manager", url = "${calendar-manager.url}")
public interface CalendarClient {

    @PutMapping("/event")
    void updateCalendarEvent(@RequestBody CalendarEventDto eventDto);
    @PostMapping("/event")
    void createCalendarEvent(@RequestBody CalendarEventDto eventDto);


}
