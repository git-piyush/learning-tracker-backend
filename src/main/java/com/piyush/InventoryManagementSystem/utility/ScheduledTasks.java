package com.piyush.InventoryManagementSystem.utility;

import com.piyush.InventoryManagementSystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasks {

    @Autowired
    private CategoryRepository categoryRepository;

    @Scheduled(fixedRate = 4 * 60 * 1000) // 10 minutes in milliseconds
    public void runEveryTenMinutes() {
        System.out.println("Task executed at: " + LocalDateTime.now());
        System.out.println("Keep my db and render awake, since i am using free tier");
        categoryRepository.findAll();
        System.out.println("Thank You.");
    }

}
