package com.example.queue_manager.service;

import com.example.queue_manager.model.Queue;
import com.example.queue_manager.repository.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueProducer {

    private final QueueRepository repository;

    @Scheduled(fixedRate = 100000)
    public void saveTask() {
        System.out.println("Here I am!");
        repository.save(new Queue("TODO"));
    }
}
