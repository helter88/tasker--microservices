package com.example.queue_manager.model;

import com.example.queue_manager.service.utile.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Queue {
    @Id
    private UUID uuid = UUID.randomUUID();

    private Instant executionDate;

    private Long executionTimeMillis;

    private Integer numberOfTries = 0;

    private String messageLog;

    private Boolean isConsumed;

    private String payload;

    public Queue(Instant executionDate, Long executionTimeMillis, Integer numberOfTries, String messageLog, Boolean isConsumed, String payload) {
        this.executionDate = executionDate;
        this.executionTimeMillis = executionTimeMillis;
        this.numberOfTries = numberOfTries;
        this.messageLog = messageLog;
        this.isConsumed = isConsumed;
        this.payload = payload;
    }
}
