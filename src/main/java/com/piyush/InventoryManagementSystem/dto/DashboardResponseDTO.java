package com.piyush.InventoryManagementSystem.dto;

import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class DashboardResponseDTO {
    private Long id;

    private Long totalQuestions;

      private Long totalQuestionsAddedByYou;

      private Long userBookmarked;

      private Map<String, Long> countMap;
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
                                Long userBookmarked,
                                Map<String, Long> countMap) {
        this.id = id;
        this.totalQuestions = totalQuestions;
        this.totalQuestionsAddedByYou = totalQuestionsAddedByYou;
        this.userBookmarked = userBookmarked;
        this.countMap = countMap;
    }
}
