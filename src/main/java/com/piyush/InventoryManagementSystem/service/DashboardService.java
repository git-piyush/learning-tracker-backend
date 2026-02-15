package com.piyush.InventoryManagementSystem.service;

import com.piyush.InventoryManagementSystem.dto.DashboardResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface DashboardService {
    public DashboardResponseDTO getDashboardData();
}
