package com.tasker.calendar_manager.client;

import java.time.Instant;

public record CalendarEventDto(
        String id,
        Instant date,
        String task,
        String description
) {
}
