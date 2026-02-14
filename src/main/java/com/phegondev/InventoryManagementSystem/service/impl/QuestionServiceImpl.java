package com.phegondev.InventoryManagementSystem.service.impl;

import com.phegondev.InventoryManagementSystem.dto.Response;
import com.phegondev.InventoryManagementSystem.entity.Question;
import com.phegondev.InventoryManagementSystem.exceptions.NotFoundException;
import com.phegondev.InventoryManagementSystem.repository.QuestionRepository;
import com.phegondev.InventoryManagementSystem.service.QuestionService;
import com.phegondev.InventoryManagementSystem.utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserUtility userUtility;

    @Override
    public Response saveQuestion(Question question, MultipartFile file) throws IOException {

        question.setUser(userUtility.getLoggedInUser());
        question.setImage(file.getBytes());
        questionRepository.save(question);
        return Response.builder()
                .status(200)
                .message("Product successfully saved")
                .build();
    }

    @Override
    public Response updateQuestion(Question question) {
        return null;
    }

    @Override
    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {

        Question question = questionRepository.findById(id).orElseThrow(()-> new NotFoundException("Category Not Found"));

        return question;
    }

    @Override
    public Response deleteQuestion(Long id) {
        return null;
    }
}
