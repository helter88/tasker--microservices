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
    public UUID uuid = UUID.randomUUID();
    public String name;
    public String description;
}
