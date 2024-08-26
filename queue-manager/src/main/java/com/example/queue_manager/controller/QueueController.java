package com.example.queue_manager.controller;

import com.example.queue_manager.model.Queue;
import com.example.queue_manager.repository.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QueueController {

    private final QueueRepository repository;
    @PostMapping("/tasks")
    public void addNewTask() {
        System.out.println("Controller runs");
        repository.save(new Queue("TODO"));
    }

}
