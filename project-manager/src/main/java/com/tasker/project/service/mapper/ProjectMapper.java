package com.tasker.project.service.mapper;

import com.tasker.project.model.Project;
import com.tasker.project.service.dto.ProjectDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toEntity(ProjectDto projectDto);
    ProjectDto toDto(Project task);

    List<ProjectDto> toListDto(List<Project> tasks);
}
