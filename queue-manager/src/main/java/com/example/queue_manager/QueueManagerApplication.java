package com.example.queue_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class QueueManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueueManagerApplication.class, args);
	}

}
