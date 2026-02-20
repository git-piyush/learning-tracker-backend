package com.piyush.InventoryManagementSystem.service;

import com.piyush.InventoryManagementSystem.dto.FeedbackCountDTO;
import com.piyush.InventoryManagementSystem.dto.FeedbackResponse;
import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public interface FeedBackService {

    void saveFeedback(Feedback feedback);

}
