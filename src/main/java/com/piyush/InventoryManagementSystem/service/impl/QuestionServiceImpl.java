package com.piyush.InventoryManagementSystem.service.impl;

import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.Question;
import com.piyush.InventoryManagementSystem.exceptions.NotFoundException;
import com.piyush.InventoryManagementSystem.repository.QuestionRepository;
import com.piyush.InventoryManagementSystem.service.QuestionService;
import com.piyush.InventoryManagementSystem.utility.UserUtility;
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
        if(file!=null){
            question.setImage(file.getBytes());
        }
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
    public List<Question> getAllUserQuestion(String userEmail) {
        List<Question> list = questionRepository.findAll();
       list= list.stream().filter(l->l.getCreatedBy().equalsIgnoreCase(userEmail)).toList();
        return list;
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
