package com.example.queue_manager.repository;

import com.example.queue_manager.model.Queue;
import com.example.queue_manager.service.utile.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface QueueRepository extends JpaRepository<Queue, UUID> {
    List<Queue> findAllByStatus(Status status);

    @Query("SELECT q FROM Queue q WHERE q.status = :status OR (q.status = 'FAILED' AND q.numberOfTries < :maxRetries)")
    List<Queue> findByStatusOrFailedWithRetriesLeft(@Param("status") Status status, @Param("maxRetries") Integer maxRetries);
}
