package com.tasker.issue.service.dto;

import java.util.UUID;

public record IssueDto(UUID uuid, String name, String description) {
}
