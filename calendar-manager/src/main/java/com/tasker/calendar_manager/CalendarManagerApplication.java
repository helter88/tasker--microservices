package com.tasker.calendar_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CalendarManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarManagerApplication.class, args);
	}

}
