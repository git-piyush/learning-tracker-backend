package com.phegondev.InventoryManagementSystem.repository;

import com.phegondev.InventoryManagementSystem.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findByCategoryContainingAndRefCodeContainingAndRefCodeLongNameContainingAndActiveContaining(
            String category,
            String refCode,
            String refCodeLongName,
            String active,
            Pageable pageable
    );

    Optional<Category> findByRefCode(String refCode);

    List<Category> findByCategory(String category);
}
