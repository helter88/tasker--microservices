package com.tasker.calandar_manager_entrance.service.mapper;

import com.tasker.calandar_manager_entrance.dto.CalendarEventDto;
import com.tasker.calandar_manager_entrance.model.CalendarEvent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CalendarEventMapper {
    CalendarEvent toEntity(CalendarEventDto calendarEventDto);
    CalendarEventDto toDto(CalendarEvent calendarEvent);

    List<CalendarEventDto> toListDto(List<CalendarEvent> tasks);
}
