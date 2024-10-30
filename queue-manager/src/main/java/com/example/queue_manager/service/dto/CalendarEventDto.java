package com.example.queue_manager.service.dto;

import java.time.Instant;
import java.util.UUID;

public record CalendarEventDto(
         String id,
         Instant date,
         String task,
         String description
) {
}
