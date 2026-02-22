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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Long> getDailyQuestionCountForCurrentMonth() {

        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);

        int month = today.getMonthValue();
        int year = today.getYear();

        // 🔹 Step 1: Initialize all days with ZERO
        Map<LocalDate, Long> tempMap = new LinkedHashMap<>();

        for (LocalDate date = startOfMonth; !date.isAfter(today); date = date.plusDays(1)) {
            tempMap.put(date, 0L);
        }

        // 🔹 Step 2: Fetch actual counts from DB
        List<Object[]> results =
                questionRepository.getDailyQuestionCount(month, year);

        for (Object[] row : results) {
            LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
            Long count = (Long) row[1];

            if (tempMap.containsKey(date)) {
                tempMap.put(date, count);
            }
        }

        // 🔹 Step 3: Format key as "Feb-15"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd");

        Map<String, Long> finalMap = new LinkedHashMap<>();
        tempMap.forEach((date, count) ->
                finalMap.put(date.format(formatter), count)
        );

        return finalMap;
    }
}
