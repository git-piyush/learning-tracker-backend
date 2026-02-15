package com.piyush.InventoryManagementSystem.repository;

import com.piyush.InventoryManagementSystem.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Long countByCreatedBy(String createdBy);

    Long countByBookmarkAndCreatedBy(String bookmark, String email);
}
