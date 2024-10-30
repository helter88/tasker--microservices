package com.example.queue_manager.client;

import com.example.queue_manager.service.dto.CalendarEventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "calendar-entrance-manager", url = "${calendar-entrance-manager.url}")
public interface CalendarEntranceClient {

    @PutMapping("/refresh")
    void refreshCalendarEvent(@RequestBody CalendarEventDto eventDto);
}
