package com.piyush.InventoryManagementSystem.repository;

import com.piyush.InventoryManagementSystem.entity.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {

}