package com.piyush.InventoryManagementSystem.controller;

import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.dto.TodoDTO;
import com.piyush.InventoryManagementSystem.entity.Feedback;
import com.piyush.InventoryManagementSystem.entity.ToDo;
import com.piyush.InventoryManagementSystem.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
@Slf4j
public class TodoController {


    @Autowired
    private TodoService todoService;

    @PostMapping("/todo")
    public ResponseEntity<Response> saveTodo(@RequestBody ToDo todo){
        return ResponseEntity.ok(todoService.createTodo(todo));
    }

    @PostMapping("/todo-list")
    public ResponseEntity<Response> saveTodoList(@RequestBody List<TodoDTO> todoList){

        todoService.deleteTodoByUserid();

        for(TodoDTO toDoDTO:todoList){
            ToDo toDo = new ToDo();
            if(toDoDTO!=null){
                if(toDoDTO.getId()!=null){
                    toDo.setId(toDoDTO.getId());
                }
                if(toDoDTO.getCompleted()!=null && !toDoDTO.getCompleted().trim().equalsIgnoreCase("")){
                    toDo.setCompleted(toDoDTO.getCompleted().trim());
                }
                if(toDoDTO.getTask()!=null && !toDoDTO.getTask().trim().equalsIgnoreCase("")){
                    toDo.setTask(toDoDTO.getTask().trim());
                }

            }
            ResponseEntity.ok(todoService.createTodo(toDo));
        }
        return ResponseEntity.ok(Response.builder().status(200).message("Activities has been added.").build());
    }

    @GetMapping("/todo")
    public ResponseEntity<Response> getTodo(){
        return ResponseEntity.ok(todoService.getAllTodoByUser());
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Response> deleteTodo(@PathVariable Long id){
        return ResponseEntity.ok(todoService.deleteTodo(id));
    }

}
