package com.tasker.project.service;

import com.tasker.project.client.IssueClient;
import com.tasker.project.repository.ProjectRepository;
import com.tasker.project.service.dto.ProjectDto;
import com.tasker.project.service.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository repository;
    private final TaskMapper mapper;
    private final IssueClient client;

    public List<ProjectDto> getProjects() {
        return mapper.toListDto(repository.findAll());
    }

    public void createProject(ProjectDto projectDto) {
        repository.save(mapper.toEntity(projectDto));
    }

    public void updateProject(ProjectDto projectDto, UUID id) {
        var found = repository.findById(id);
        found.ifPresentOrElse( task -> {
            task.setDescription(projectDto.description());
            repository.save(task);
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    public void deleteProject(UUID id) {
        var found = repository.findById(id);
        found.ifPresentOrElse( task -> {
            repository.deleteById(id);
        }, () -> {
            throw new NoSuchElementException();
        });
    }
}
