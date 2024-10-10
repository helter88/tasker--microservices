package com.tasker.project.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Project {
    @Id
    private UUID uuid = UUID.randomUUID();
    private String description;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    public List<ProjectEvent> projectEventList;
}
