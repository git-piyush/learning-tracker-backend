package com.phegondev.InventoryManagementSystem.controller;

import com.phegondev.InventoryManagementSystem.constants.AppConstants;
import com.phegondev.InventoryManagementSystem.dto.CategoryDTO;
import com.phegondev.InventoryManagementSystem.dto.Response;
import com.phegondev.InventoryManagementSystem.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllCategories(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE) int page,
                                                     @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                                     @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = AppConstants.DEFAULT_ORDER) String order,
                                                     @RequestParam(value ="category", required = false, defaultValue = "") String category,
                                                     @RequestParam(value ="refCode", required = false, defaultValue = "") String refCode,
                                                     @RequestParam(value ="longName", required = false, defaultValue = "") String longName,
                                                     @RequestParam(value ="active", required = false, defaultValue = "") String active) {
        return ResponseEntity.ok(categoryService.getAllCategories(page,size,orderBy,order,category,refCode,longName,active));
    }

    @GetMapping("/category-list")
    public ResponseEntity<Response> getAllCategoryList(){

        List<String> categoryList =  categoryService.getAllCategoryList();

        return ResponseEntity.ok(Response.builder()
                .status(200)
                .message("success")
                .categoryList(categoryList)
                .build());

    }

    @GetMapping("/get-category/{id}")
    public ResponseEntity<Response> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/categoryByCategory/{id}")
    public ResponseEntity<Response> getCategoryByCategory(@PathVariable String id) {
       return ResponseEntity.ok(Response.builder()
                .status(200)
                .message("success")
                .catMap(categoryService.getCategoryByCategory(id))
                .build());
    }

    @DeleteMapping("/delete-category/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }


}
