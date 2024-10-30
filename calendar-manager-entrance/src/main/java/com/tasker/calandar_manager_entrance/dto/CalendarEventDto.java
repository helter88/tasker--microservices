package com.tasker.calandar_manager_entrance.dto;

import java.time.Instant;
import java.util.UUID;

public record CalendarEventDto(String id, Instant date, String task, String description) {
}
