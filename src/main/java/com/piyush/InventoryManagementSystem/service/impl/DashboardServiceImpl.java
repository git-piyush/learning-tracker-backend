package com.piyush.InventoryManagementSystem.service.impl;

import com.piyush.InventoryManagementSystem.dto.DashboardResponseDTO;
import com.piyush.InventoryManagementSystem.entity.Dashboard;
import com.piyush.InventoryManagementSystem.entity.FeedBackDetails;
import com.piyush.InventoryManagementSystem.entity.Question;
import com.piyush.InventoryManagementSystem.entity.ToDo;
import com.piyush.InventoryManagementSystem.repository.DashboardRepository;
import com.piyush.InventoryManagementSystem.repository.QuestionRepository;
import com.piyush.InventoryManagementSystem.repository.TodoRepository;
import com.piyush.InventoryManagementSystem.service.DashboardService;
import com.piyush.InventoryManagementSystem.service.FeedBackService;
import com.piyush.InventoryManagementSystem.service.FeedbackDetailsService;
import com.piyush.InventoryManagementSystem.service.QuestionService;
import com.piyush.InventoryManagementSystem.utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private FeedbackDetailsService feedbackDetailsService;

    @Autowired
    private UserUtility utility;

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public DashboardResponseDTO getDashboardData() {
        Long questionCount = questionRepository.countByCreatedBy(utility.getLoggedInUser().getEmail());

        Long bookMarked = questionRepository.countByBookmarkAndCreatedBy("Yes",utility.getLoggedInUser().getEmail());

        Dashboard dashboard = dashboardRepository.findAll().getFirst();

        List<Question> categoryList = questionRepository.findAll();

        Map<String, Long> countMap = categoryList.stream()
                .collect(Collectors.groupingBy(
                        Question::getSubCategory,   // key = subCategory
                        Collectors.counting()       // value = count of each subCategory
                ));

        //Total Questions: present in dashboard
        List<FeedBackDetails> feedBackDetailsList = feedbackDetailsService.getUnreadFeedbackByUser(utility.getLoggedInUser().getId());

        Long unreadCount = (long) feedBackDetailsList.size();

        DashboardResponseDTO dashboardResponseDTO = new DashboardResponseDTO(dashboard.getId(),
                dashboard.getTotalquestions(),
                questionCount,
                bookMarked,
                countMap,unreadCount);

        //Add Your Activities & Events:- Dashboard section Data
        List<ToDo> toDoList = todoRepository.findByUserId(utility.getLoggedInUser().getId());
        dashboardResponseDTO.setToDoList(toDoList);

        Map<String, Long> dailyQuestionCountMap = new HashMap<>();

        dailyQuestionCountMap = questionService.getDailyQuestionCountForCurrentMonth();

        dashboardResponseDTO.setDailyQuestionCountMap(dailyQuestionCountMap);

        return dashboardResponseDTO;
    }
}
