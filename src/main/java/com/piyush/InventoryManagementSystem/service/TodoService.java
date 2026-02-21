package com.piyush.InventoryManagementSystem.service;

import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.ToDo;
import org.springframework.stereotype.Service;

@Service
public interface TodoService {

    Response createTodo(ToDo todo);

    Response updateTodo(ToDo todo);

    Response deleteTodo(Long id);

    Response getAllTodoByUser();


    void deleteTodoByUserid();
}
