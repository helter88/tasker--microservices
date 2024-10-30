package com.example.queue_manager.controller;

import java.time.Instant;

public record CalendarEventDto(
        String id,
        Instant date,
        String task,
        String description
) {
}