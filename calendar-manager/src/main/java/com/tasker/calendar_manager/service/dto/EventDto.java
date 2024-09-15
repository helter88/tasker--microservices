package com.tasker.calendar_manager.service.dto;

import java.time.Instant;

public record EventDto(Instant date, String task) {
}
