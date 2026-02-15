package com.phegondev.InventoryManagementSystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class DashboardResponseDTO {
    private Long id;

    private Long totalQuestions;

      private Long totalQuestionsAddedByYou;

      private Long lastFiveQuestions;
//
//    private Long newMessages;
//
//    private Long feedBack;
//
//    private Long javaTheoritical;
//
//    private Long javaProgramming;
//
//    private Long springboot;
//
//    private Long sql;

    public DashboardResponseDTO(Long id,
                                Long totalQuestions,
                                Long totalQuestionsAddedByYou,
                                Long lastFiveQuestions) {
        this.id = id;
        this.totalQuestions = totalQuestions;
        this.totalQuestionsAddedByYou = totalQuestionsAddedByYou;
        this.lastFiveQuestions = lastFiveQuestions;
    }
}
