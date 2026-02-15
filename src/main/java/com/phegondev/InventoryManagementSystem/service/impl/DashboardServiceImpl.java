package com.phegondev.InventoryManagementSystem.service.impl;

import com.phegondev.InventoryManagementSystem.dto.DashboardResponseDTO;
import com.phegondev.InventoryManagementSystem.entity.Dashboard;
import com.phegondev.InventoryManagementSystem.entity.Question;
import com.phegondev.InventoryManagementSystem.repository.DashboardRepository;
import com.phegondev.InventoryManagementSystem.repository.QuestionRepository;
import com.phegondev.InventoryManagementSystem.service.DashboardService;
import com.phegondev.InventoryManagementSystem.utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserUtility utility;

    @Override
    public DashboardResponseDTO getDashboardData() {
        Long questionCount = questionRepository.countByCreatedBy(utility.getLoggedInUser().getEmail());
        Dashboard dashboard = dashboardRepository.findAll().getFirst();
        DashboardResponseDTO dashboardResponseDTO = new DashboardResponseDTO(dashboard.getId(), dashboard.getTotalquestions(),questionCount, dashboard.getLastFiveQuestions());
        return dashboardResponseDTO;
    }
}
