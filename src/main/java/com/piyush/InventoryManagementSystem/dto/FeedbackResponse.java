package com.piyush.InventoryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FeedbackResponse {

    private Long id;

    private Integer rating;

    private String message;

    private LocalDateTime submittedOn;

    private String submittedBy;

    public FeedbackResponse(Long id, Integer rating, String message, LocalDateTime createdAt, String createdBy) {
        this.id = id;
        this.rating = rating;
        this.message = message;
        this.submittedOn = createdAt;
        this.submittedBy = createdBy;
    }
}
