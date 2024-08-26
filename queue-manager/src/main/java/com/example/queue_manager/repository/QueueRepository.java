package com.example.queue_manager.repository;

import com.example.queue_manager.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QueueRepository extends JpaRepository<Queue, UUID> {
}
