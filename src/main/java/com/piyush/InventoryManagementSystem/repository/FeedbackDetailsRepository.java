package com.piyush.InventoryManagementSystem.repository;


import com.piyush.InventoryManagementSystem.entity.FeedBackDetails;
import com.piyush.InventoryManagementSystem.service.FeedbackDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface FeedbackDetailsRepository extends JpaRepository<FeedBackDetails, Long> {

    List<FeedbackDetailsService> findByReadUserId(Long userId);

    List<FeedBackDetails> findByReadUserIdIsNullOrReadUserIdNot(Long userId);

    @Query("""
    SELECT f FROM FeedBackDetails f
    WHERE((:seen = 'Y' AND f.readUserId = :userId) OR
          (:seen = 'N' AND f.readUserId IS NULL) OR (:seen IS NULL))
    AND f.rating = COALESCE(:rating, f.rating)
    AND f.createdAt >= COALESCE(:startDate, f.createdAt)
    AND f.createdAt <= COALESCE(:endDate, f.createdAt)
    """)
    Page<FeedBackDetails> findAllFeedbackDetails(
            @Param("userId") Long userId,
            @Param("rating") Integer rating,
            @Param("seen") String seen,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );

}
