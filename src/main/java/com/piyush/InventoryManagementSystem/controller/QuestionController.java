package com.piyush.InventoryManagementSystem.controller;

import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.Question;
import com.piyush.InventoryManagementSystem.service.QuestionService;
import com.piyush.InventoryManagementSystem.utility.UserUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserUtility utility;

    @PostMapping("/add-question")
    public ResponseEntity<Response> saveQuestion(
            @RequestParam("category") String  category,
            @RequestParam("subCategory") String  subCategory,
            @RequestParam("type") String  type,
            @RequestParam("question") String  question,
            @RequestParam("answer") String  answer,
            @RequestParam("bookmark") String  bookmark,
            @RequestParam("level") String  level,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile
    ) throws IOException {
        Question question1 = new Question();
        question1.setCategory(category);
        question1.setType(type);
        question1.setQuestion(question);
        question1.setAnswer(answer);
        question1.setBookmark(bookmark);
        question1.setLevel(level);
        question1.setSubCategory(subCategory);
        return ResponseEntity.ok(questionService.saveQuestion(question1, imageFile));
    }

    @GetMapping("/get-question/{id}")
    public ResponseEntity<Response> getQuestionById(@PathVariable Long id){
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(Response.builder()
                .status(200)
                .question(question)
                .message("Question retrieved successfully.")
                .build());
    }

    //
    @GetMapping("/getall-question")
    public ResponseEntity<Response> getQuestions(){
        List<Question> questionList = questionService.getAllUserQuestion(utility.getLoggedInUser().getEmail());
        return ResponseEntity.ok(Response.builder()
                .status(200)
                .questionList(questionList)
                .message("Question retrieved successfully.")
                .build());
    }



}
