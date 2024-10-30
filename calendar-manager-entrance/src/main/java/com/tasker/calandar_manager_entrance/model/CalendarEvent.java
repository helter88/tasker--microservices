package com.tasker.calandar_manager_entrance.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Document
public class CalendarEvent {
    @Id
    private String id = UUID.randomUUID().toString();
    private Instant date;
    private String task;
    private String description;
}
