package com.tasker.project.service;

import com.tasker.project.client.CalendarClient;
import com.tasker.project.client.IssueClient;
import com.tasker.project.exeption.CreationCalendarEventException;
import com.tasker.project.exeption.InvalidResponseFromCalendarException;
import com.tasker.project.model.ProjectEvent;
import com.tasker.project.repository.ProjectEventRepository;
import com.tasker.project.repository.ProjectRepository;
import com.tasker.project.service.dto.EventDto;
import com.tasker.project.service.dto.ProjectDto;
import com.tasker.project.service.dto.ProjectEventDto;
import com.tasker.project.service.dto.ResponseEventDto;
import com.tasker.project.service.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository repository;
    private final ProjectMapper mapper;
    private final IssueClient client;
    private final CalendarClient calendarClient;
    private final ProjectEventRepository projectEventRepository;

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

    //toDo Czy tą metodę oznaczyć adnotacją Transactional ?
    @Transactional
    public void createProjectAndEvent(ProjectEventDto projectEventDto) {
        var project = repository.save(mapper.toEntity(projectEventDto.projectDto()));
        var eventDto = projectEventDto.eventDto();
        var eventDtoWithDescription = new EventDto(eventDto.date(), eventDto.task(), project.getUuid().toString());
        var response  = calendarClient.saveEvent(eventDtoWithDescription);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CreationCalendarEventException("Failed to create event in calendar. Status: " + response.getStatusCode());
        }
        ResponseEventDto responseBody = response.getBody();
        if (responseBody == null || responseBody.id() == null) {
            throw new InvalidResponseFromCalendarException("Received invalid response from calendar service");
        }

        project.getProjectEventList().add(ProjectEvent.builder()
                .project(repository.findById(project.getUuid()).orElseThrow())
                .eventId(responseBody.id()).build());

    }
}
