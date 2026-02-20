package com.piyush.InventoryManagementSystem.service.impl;

import com.piyush.InventoryManagementSystem.dto.CategoryDTO;
import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.Category;
import com.piyush.InventoryManagementSystem.entity.FeedBackDetails;
import com.piyush.InventoryManagementSystem.repository.FeedbackDetailsRepository;
import com.piyush.InventoryManagementSystem.service.FeedbackDetailsService;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class FeedBackDetailsServiceImpl implements FeedbackDetailsService {

    @Autowired
    private FeedbackDetailsRepository feedbackDetailsRepository;

    @Override
    public List<FeedBackDetails> getUnreadFeedbackByUser(Long userId) {
        return feedbackDetailsRepository.findByReadUserIdIsNullOrReadUserIdNot(userId);
    }

    @Override
    public Response getAllFeedbackDetails(Long userId, int page, int size, String orderBy, String order, Integer rating, LocalDateTime startDate, LocalDateTime endDate, String seen) {

        Sort sort = order.equalsIgnoreCase("asc")
                ? Sort.by(orderBy).ascending()
                : Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<FeedBackDetails> page1 = feedbackDetailsRepository
                .findAllFeedbackDetails(
                        userId,
                        rating,
                        seen,
                        startDate,
                        endDate,
                        pageable
                );

        List<FeedBackDetails> feedBackDetailsList = page1.getContent();


       // List<CategoryDTO> categoryDTOS = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {}.getType());

        return Response.builder()
                .status(200)
                .message("success")
                .feedBackDetails(feedBackDetailsList)
                .totalPages(page1.getTotalPages())
                .totalElements(page1.getTotalElements())
                .last(page1.isLast())
                .first(page1.isFirst())
                .empty(page1.isEmpty())
                .build();
    }
}
