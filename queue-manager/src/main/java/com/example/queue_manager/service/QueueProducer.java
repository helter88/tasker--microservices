package com.example.queue_manager.service;

import com.example.queue_manager.infrastructure.HandleQueueEntry;
import com.example.queue_manager.model.Queue;
import com.example.queue_manager.repository.QueueRepository;
import com.example.queue_manager.service.dto.CalendarEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class QueueProducer {
    private final QueueRepository queueRepository;
    private final ObjectMapper objectMapper;
    @HandleQueueEntry
    public Queue createCalendarEventQueue(CalendarEventDto eventDto) throws JsonProcessingException {
        // Nie stosuj GSONA do serializacji bo nie radzi sobie z Instant !!!!
        String jsonEventDto = objectMapper.writeValueAsString(eventDto);
        return new Queue(Instant.now(), null, 0, "com.example.queue_manager.service.QueueProducer.createCalendarEvent: " + eventDto.id(), false,jsonEventDto);
    }

    @HandleQueueEntry
    public Queue updateCalendarEventQueue(CalendarEventDto eventDto) throws JsonProcessingException {
        String jsonEventDto = objectMapper.writeValueAsString(eventDto);
        return new Queue(Instant.now(), null, 0, "com.example.queue_manager.service.QueueProducer.updateCalendarEvent: " + eventDto.id(), false, jsonEventDto);
    }

    @HandleQueueEntry
    public Queue refreshCalendarEventQueue(CalendarEventDto eventDto) throws JsonProcessingException {
        // Nie stosuj GSONA do serializacji bo nie radzi sobie z Instant !!!!
        String jsonEventDto = objectMapper.writeValueAsString(eventDto);
        return new Queue(Instant.now(), null, 0, "com.example.queue_manager.service.QueueProducer.refreshCalendarEvent: " + eventDto.id(), false,jsonEventDto);
    }
}
