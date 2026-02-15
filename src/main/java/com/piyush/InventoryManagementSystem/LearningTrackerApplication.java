package com.piyush.InventoryManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LearningTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningTrackerApplication.class, args);
	}

}
