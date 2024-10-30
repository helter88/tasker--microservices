package com.tasker.calendar_manager.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "queue-manager", url = "${queue-manager.url}")
public interface QueueManagerClient {

    @PostMapping("/calendar/refresh")
    void refreshEvent(@RequestBody CalendarEventDto calendarEventDto);
}
