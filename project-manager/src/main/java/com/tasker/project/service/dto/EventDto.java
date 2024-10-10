package com.tasker.project.service.dto;

import java.time.Instant;

public record EventDto(Instant date, String task, String description) {
}
