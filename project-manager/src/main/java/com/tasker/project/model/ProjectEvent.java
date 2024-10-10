package com.tasker.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
public class ProjectEvent {
    @Id
    //oznaczać przy wartościach domyślnych jak używamy builder
    @Builder.Default
    private UUID uuid = UUID.randomUUID();
    private String eventId;
    private UUID projectId;
    @ManyToOne
    private Project project;
}
