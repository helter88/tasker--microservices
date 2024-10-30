package com.tasker.calandar_manager_entrance.client;

import com.tasker.calandar_manager_entrance.model.CalendarEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "queue-manager", url = "${queue-manager.url}")
public interface QueueManagerClient {
    @PostMapping("/calendar")
    void sendEvent(@RequestBody CalendarEvent calendarEvent);

    @PutMapping("/calendar")
    void sendUpdatedEvent (@RequestBody CalendarEvent calendarEvent);
}
