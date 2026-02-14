package com.phegondev.InventoryManagementSystem.service;

import com.phegondev.InventoryManagementSystem.dto.Response;
import com.phegondev.InventoryManagementSystem.entity.Question;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface QuestionService {
    Response saveQuestion(Question question, MultipartFile file) throws IOException;
    Response updateQuestion(Question question);
    List<Question> getAllQuestion();
    Question getQuestionById(Long id);
    Response deleteQuestion(Long id);
}
