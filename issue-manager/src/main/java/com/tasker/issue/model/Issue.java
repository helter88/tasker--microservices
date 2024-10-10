package com.tasker.issue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Issue {
    @Id
    private UUID uuid = UUID.randomUUID();
    private String name;
    private String description;
}
