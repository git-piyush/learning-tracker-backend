package com.phegondev.InventoryManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.phegondev.InventoryManagementSystem.entity.Question;
import com.phegondev.InventoryManagementSystem.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    //generic
    private int status;
    private String message;
    //for login
    private String token;
    private UserRole role;
    private String expirationTime;

    //for pagination
    private Integer totalPages;
    private Long totalElements;

    private Boolean first;
    private Boolean last;
    private Boolean empty;

    private CategoryDTO category;
    private List<CategoryDTO> categories;
    private List<String> categoryList;
    private Map<String,String> catMap;

    //data output optional
    private UserDTO user;
    private List<UserDTO> users;

    private Question question;
    private List<Question> questionList;

    private LocalDateTime timestamp = LocalDateTime.now();

}
