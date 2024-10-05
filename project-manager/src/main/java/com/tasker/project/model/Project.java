package com.tasker.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Project {
    @Id
    public UUID uuid = UUID.randomUUID();
    public String description;
}
