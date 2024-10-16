package com.example.queue_manager.controller;

import com.example.queue_manager.exception.TimerAnnotation;
import com.example.queue_manager.model.Queue;
import com.example.queue_manager.repository.QueueRepository;
import com.example.queue_manager.service.utile.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QueueController {

    private final QueueRepository repository;

    @PostMapping("/event")
    @TimerAnnotation
    public ResponseEntity<String> addNewEvent() {
        repository.save(new Queue(Status.TODO));
        return ResponseEntity.ok("xD");
    }

}
