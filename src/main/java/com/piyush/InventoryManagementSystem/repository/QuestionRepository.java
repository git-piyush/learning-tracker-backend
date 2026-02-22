package com.piyush.InventoryManagementSystem.repository;

import com.piyush.InventoryManagementSystem.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByCreatedBy(String createdBy);

    Long countByCreatedBy(String createdBy);

    Long countByBookmarkAndCreatedBy(String bookmark, String email);

    @Query("""
        SELECT FUNCTION('DATE', q.createdDate), COUNT(q)
        FROM Question q
        WHERE MONTH(q.createdDate) = :month
          AND YEAR(q.createdDate) = :year
        GROUP BY FUNCTION('DATE', q.createdDate)
    """)
    List<Object[]> getDailyQuestionCount(
            @Param("month") int month,
            @Param("year") int year
    );
}
