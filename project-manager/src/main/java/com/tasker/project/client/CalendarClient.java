package com.tasker.project.client;

import com.tasker.project.service.dto.EventDto;
import com.tasker.project.service.dto.ResponseEventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "calendar-manager", url = "http://localhost:8092/api/v1/calendar" )
public interface CalendarClient {
    @PostMapping("/event")
    ResponseEntity<ResponseEventDto> saveEvent(@RequestBody EventDto eventDto);
}
