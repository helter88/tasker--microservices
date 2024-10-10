package com.tasker.project.repository;

import com.tasker.project.model.ProjectEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectEventRepository extends JpaRepository<ProjectEvent,UUID> {
}
