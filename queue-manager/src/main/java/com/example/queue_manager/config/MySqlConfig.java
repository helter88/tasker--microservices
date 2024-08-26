package com.example.queue_manager.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.example.queue_manager.model")
public class MySqlConfig {
}
