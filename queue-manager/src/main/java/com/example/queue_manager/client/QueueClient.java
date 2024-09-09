package com.example.queue_manager.client;

import com.example.queue_manager.exception.TimerAnnotation;
import com.example.queue_manager.model.Player;
import com.example.queue_manager.service.dto.PlayerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "queue-manager", url = "http://rest.nbaapi.com/api" )
public interface QueueClient {

    @GetMapping("/PlayerDataAdvanced/quer")
    @TimerAnnotation
    List<PlayerDto> processDataForJob();
}
