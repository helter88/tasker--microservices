package com.tasker.calendar_manager.service.dto;

import java.time.Instant;

public record EventDto(String id, Instant date, String task, String description) {
}
