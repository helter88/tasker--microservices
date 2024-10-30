package com.example.queue_manager.repository;

import com.example.queue_manager.model.Queue;
import com.example.queue_manager.service.utile.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface QueueRepository extends JpaRepository<Queue, UUID> {
    @Query("""
    SELECT q1 FROM Queue q1
    WHERE q1.executionDate = (
        SELECT MAX(q2.executionDate)
        FROM Queue q2
        WHERE SUBSTRING(q2.messageLog, LOCATE('updateCalendarEvent:', q2.messageLog) + 21) =
              SUBSTRING(q1.messageLog, LOCATE('updateCalendarEvent:', q1.messageLog) + 21)
    )
    AND q1.messageLog LIKE '%updateCalendarEvent%'
    """)
    List<Queue> findLatestUpdatedQueueEntriesForEachIdentifier();

    @Query("""
    SELECT q1 FROM Queue q1
    WHERE q1.executionDate = (
        SELECT MAX(q2.executionDate)
        FROM Queue q2
        WHERE SUBSTRING(q2.messageLog, LOCATE('createCalendarEvent:', q2.messageLog) + 21) =
              SUBSTRING(q1.messageLog, LOCATE('createCalendarEvent:', q1.messageLog) + 21)
    )
    AND q1.messageLog LIKE '%createCalendarEvent%'
    """)
    List<Queue> findLatestCreatedQueueEntriesForEachIdentifier();

    List<Queue> findAllByMessageLogContainingAndIsConsumedEquals(String str, boolean isConsumed);
}
