package com.example.queue_manager.model;

import com.example.queue_manager.service.utile.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Queue {
    @Id
    UUID uuid = UUID.randomUUID();

    @Enumerated(EnumType.STRING)
    Status status;

    Instant executionDate;

    Duration executionTime;

    Integer numberOfTries = 0;

    String messageLog;

    public Queue(Status status) {
        this.status = status;
    }
}
