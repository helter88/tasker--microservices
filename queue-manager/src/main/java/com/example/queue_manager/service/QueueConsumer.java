package com.example.queue_manager.service;

import com.example.queue_manager.client.CalendarClient;
import com.example.queue_manager.client.CalendarEntranceClient;
import com.example.queue_manager.repository.QueueRepository;
import com.example.queue_manager.service.dto.CalendarEventDto;
import com.example.queue_manager.service.utile.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class QueueConsumer {
    private final CalendarClient calendarClient;
    private final CalendarEntranceClient calendarEntranceClient;
    private final QueueRepository queueRepository;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelayString = "${queue.repetition-time}")
    public void processCalendarUpdate() {
        var ques = queueRepository.findLatestUpdatedQueueEntriesForEachIdentifier();
        ques.forEach(queue -> {
            if(!queue.getIsConsumed()){
                try {
                    CalendarEventDto eventDto = objectMapper.readValue(queue.getPayload(), CalendarEventDto.class);
                    calendarClient.updateCalendarEvent(eventDto);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Deserialization problem");
                }
                queue.setIsConsumed(true);
                queueRepository.save(queue);
            } else {
                log.info("Already consumed: " + queue);
            }
        });
    }
    @Scheduled(fixedDelayString = "${queue.repetition-time}")
    public void processCalendarCreate() {
        var ques = queueRepository.findLatestCreatedQueueEntriesForEachIdentifier();
        ques.forEach(queue -> {
            if(!queue.getIsConsumed()){
                try {
                    CalendarEventDto eventDto = objectMapper.readValue(queue.getPayload(), CalendarEventDto.class);
                    calendarClient.createCalendarEvent(eventDto);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Deserialization problem");
                }
                queue.setIsConsumed(true);
                queueRepository.save(queue);
            } else {
                log.info("Already consumed: " + queue);
            }
        });
    }

    @Scheduled(fixedDelayString = "${queue.repetition-time}")
    public void processRefresh() {
        var ques = queueRepository.findAllByMessageLogContainingAndIsConsumedEquals("refreshCalendarEvent", false);
        ques.forEach(queue -> {
                try {
                    CalendarEventDto eventDto = objectMapper.readValue(queue.getPayload(), CalendarEventDto.class);
                    calendarEntranceClient.refreshCalendarEvent(eventDto);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Deserialization problem");
                }
                queue.setIsConsumed(true);
                queueRepository.save(queue);
        });
    }
}

