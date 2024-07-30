package com.tasker.project.service;

import com.tasker.project.client.IssueClient;
import com.tasker.project.repository.TaskRepository;
import com.tasker.project.service.dto.TaskDto;
import com.tasker.project.service.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;
    private final IssueClient client;

    public List<TaskDto> getTasks() {
        return mapper.toListDto(repository.findAll());
    }

    public void createTask(TaskDto taskDto) {
        repository.save(mapper.toEntity(taskDto));
    }

    public void updateTask(TaskDto taskDto, UUID id) {
        var found = repository.findById(id);
        found.ifPresentOrElse( task -> {
            task.setDescription(taskDto.description());
            repository.save(task);
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    public void deleteTask( UUID id) {
        var found = repository.findById(id);
        found.ifPresentOrElse( task -> {
            repository.deleteById(id);
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    public String getDataFromIssue() {
        return client.getIssueData();
    }
}
