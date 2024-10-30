package com.tasker.calandar_manager_entrance.service;

import com.tasker.calandar_manager_entrance.client.QueueManagerClient;
import com.tasker.calandar_manager_entrance.dto.CalendarEventDto;
import com.tasker.calandar_manager_entrance.exception.InvalidSaveException;
import com.tasker.calandar_manager_entrance.model.CalendarEvent;
import com.tasker.calandar_manager_entrance.repository.CalendarManagerEntranceRepository;
import com.tasker.calandar_manager_entrance.service.mapper.CalendarEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CalendarManagerEntranceService {
    private final CalendarManagerEntranceRepository entranceRepository;
    private final CalendarEventMapper mapper;
    private final QueueManagerClient queueManagerClient;

    public void saveCalendarEvent(CalendarEventDto calendarEventDto) {
        var calendarEvent = entranceRepository.save(mapper.toEntity(calendarEventDto));
        if(calendarEvent.getId().isBlank()) {
            throw new InvalidSaveException("Object not saved");
        }
        sendEventAsync(calendarEvent);
    }

    @Async
    public void sendEventAsync(CalendarEvent calendarEvent) {
        try {
            queueManagerClient.sendEvent(calendarEvent);
        } catch (Exception e) {
           throw new InvalidSaveException("Object not saved in Queue Manager");
        }
    }

    public void updateCalendarEvent(CalendarEventDto calendarEventDto) {
        if (calendarEventDto.id() == null) {
            throw new InvalidSaveException("ID cannot be null for update operation");
        }
        var existingEvent = entranceRepository.findById(calendarEventDto.id()).orElseThrow(
                () -> new InvalidSaveException("Calendar event not found")
        );
        var eventToUpdate = mapper.toEntity(calendarEventDto);
        eventToUpdate.setId(existingEvent.getId());
        var calendarEvent = entranceRepository.save(eventToUpdate);
        if(calendarEvent.getId().isBlank()) {
            throw new InvalidSaveException("Object not updated");
        }
        updateEventAsync(calendarEvent);
    }

    @Async
    public void updateEventAsync(CalendarEvent calendarEvent) {
        try {
            queueManagerClient.sendUpdatedEvent(calendarEvent);
        } catch (Exception e) {
            throw new InvalidSaveException("Object not updated in Queue Manager");
        }
    }

    public void refreshCalendarEvent(CalendarEventDto calendarEventDto) {
        if (calendarEventDto.id() == null) {
            throw new InvalidSaveException("ID cannot be null for refresh operation");
        }
        var existingEvent = entranceRepository.findById(calendarEventDto.id()).orElseThrow(
                () -> new InvalidSaveException("Calendar event not found")
        );
        var eventToUpdate = mapper.toEntity(calendarEventDto);
        eventToUpdate.setId(existingEvent.getId());
        var calendarEvent = entranceRepository.save(eventToUpdate);
        if(calendarEvent.getId().isBlank()) {
            throw new InvalidSaveException("Object not refreshed");
        }
    }
}
