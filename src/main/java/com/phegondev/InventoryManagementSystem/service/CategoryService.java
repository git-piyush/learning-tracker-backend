package com.phegondev.InventoryManagementSystem.service;

import com.phegondev.InventoryManagementSystem.dto.CategoryDTO;
import com.phegondev.InventoryManagementSystem.dto.Response;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public interface CategoryService {
    Response createCategory(CategoryDTO categoryDTO);
    Response getAllCategories(int page, int size, String sortBy, String direction, String category, String refCode,String longName,String active);
    Response getCategoryById(Long id);
    Response updateCategory(CategoryDTO categoryDTO);
    Response deleteCategory(Long id);
    List<String> getAllCategoryList();

    Map<String, String> getCategoryByCategory(String category);

    Map<String, Long> getCategoryCount();

    List<String> getSubCategory(String cat);
}
