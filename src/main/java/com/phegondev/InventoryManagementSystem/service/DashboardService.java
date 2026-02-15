package com.phegondev.InventoryManagementSystem.service;

import com.phegondev.InventoryManagementSystem.dto.DashboardResponseDTO;
import com.phegondev.InventoryManagementSystem.entity.Dashboard;
import org.springframework.stereotype.Service;

@Service
public interface DashboardService {
    public DashboardResponseDTO getDashboardData();
}
