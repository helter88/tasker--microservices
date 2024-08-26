package com.example.queue_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
    //    TODO dlaczego uuid musi być public?
    @Id
    UUID uuid = UUID.randomUUID();

    String status;

    Instant executionDate;

    Duration executionTime;

    public Queue(String status) {
        this.status = status;
    }
}
