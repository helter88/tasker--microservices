package com.tasker.project.service.mapper;

import com.tasker.project.model.Task;
import com.tasker.project.service.dto.TaskDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskDto taskDto);
    TaskDto toDto(Task task);

    List<TaskDto> toListDto(List<Task> tasks);
}
