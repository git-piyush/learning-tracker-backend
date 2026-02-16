package com.piyush.InventoryManagementSystem.repository;

import com.piyush.InventoryManagementSystem.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByCreatedBy(String createdBy);

    Long countByCreatedBy(String createdBy);

    Long countByBookmarkAndCreatedBy(String bookmark, String email);
}
