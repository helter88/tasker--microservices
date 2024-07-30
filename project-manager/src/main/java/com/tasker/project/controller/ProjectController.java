package com.tasker.project.controller;

import com.tasker.project.repository.TaskRepository;
import com.tasker.project.service.TaskService;
import com.tasker.project.service.dto.TaskDto;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectController {
    private final TaskService taskService;
    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping
    public void addTask(@RequestBody TaskDto taskDto) {
        taskService.createTask(taskDto);
    }

    @PutMapping("/{id}")
    public void modifyTask(@RequestBody TaskDto taskDto, UUID uuid) {
        taskService.updateTask(taskDto, uuid);
    }
    @DeleteMapping("/{id}")
    public void modifyTask( UUID uuid) {
        taskService.deleteTask(uuid);
    }

    @GetMapping("/issueData")
    public ResponseEntity<String> getDataFromIssue(){
        return ResponseEntity.ok(taskService.getDataFromIssue());
    }

}
