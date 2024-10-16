package com.example.queue_manager.service;

import com.example.queue_manager.model.Queue;
import com.example.queue_manager.repository.QueueRepository;
import com.example.queue_manager.service.utile.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueProducer {

    @Scheduled(fixedRate = 100000)
    public void saveTask() {
        repository.save(new Queue(Status.TODO));
    }
}
