package com.phegondev.InventoryManagementSystem.controller;

import com.phegondev.InventoryManagementSystem.dto.Response;
import com.phegondev.InventoryManagementSystem.entity.Question;
import com.phegondev.InventoryManagementSystem.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/add-question")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> saveQuestion(
            @RequestParam("category") String  category,
            @RequestParam("type") String  type,
            @RequestParam("question") String  question,
            @RequestParam("answer") String  answer,
            @RequestParam("bookmark") String  bookmark,
            @RequestParam("level") String  level,
            @RequestParam("imageFile") MultipartFile imageFile
    ) throws IOException {
        Question question1 = new Question();
        question1.setCategory(category);
        question1.setType(type);
        question1.setQuestion(question);
        question1.setAnswer(answer);
        question1.setBookmark(bookmark);
        question1.setLevel(level);
        return ResponseEntity.ok(questionService.saveQuestion(question1, imageFile));
    }

    @GetMapping("/get-question/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getQuestionById(@PathVariable Long id){
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(Response.builder()
                .status(200)
                .question(question)
                .message("Question retrieved successfully.")
                .build());
    }

    @GetMapping("/getall-question")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getQuestions(){
        List<Question> questionList = questionService.getAllQuestion();
        return ResponseEntity.ok(Response.builder()
                .status(200)
                .questionList(questionList)
                .message("Question retrieved successfully.")
                .build());
    }



}
