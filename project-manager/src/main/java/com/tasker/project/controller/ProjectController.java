package com.tasker.project.controller;

import com.tasker.project.controller.dto.ResponseDto;
import com.tasker.project.service.ProjectService;
import com.tasker.project.service.dto.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    @GetMapping
    public List<ProjectDto> getProjects() {
        return projectService.getProjects();
    }

    @PostMapping
    public ResponseEntity<?> addProject(@RequestBody ProjectDto projectDto) {
        projectService.createProject(projectDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyProject(@RequestBody ProjectDto projectDto, UUID uuid) {
        projectService.updateProject(projectDto, uuid);
        return ResponseEntity
                .ok("Modified");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject( UUID uuid) {
        projectService.deleteProject(uuid);
        return ResponseEntity
                .ok("deleted");
    }

}
