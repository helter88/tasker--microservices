package com.tasker.calandar_manager_entrance.repository;

import com.tasker.calandar_manager_entrance.model.CalendarEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CalendarManagerEntranceRepository extends MongoRepository<CalendarEvent, String> {
}
