package com.piyush.InventoryManagementSystem.service.impl;


import com.piyush.InventoryManagementSystem.dto.CategoryDTO;
import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.Category;
import com.piyush.InventoryManagementSystem.exceptions.DuplicateValueException;
import com.piyush.InventoryManagementSystem.exceptions.NotFoundException;
import com.piyush.InventoryManagementSystem.repository.CategoryRepository;
import com.piyush.InventoryManagementSystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response createCategory(CategoryDTO categoryDTO) {

        Optional<Category> existingCategory = categoryRepository.findByRefCode(categoryDTO.getRefCode());
        if (existingCategory.isPresent()) {
            throw new DuplicateValueException("Duplicate Refcode");
        }
        Category categoryToSave = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(categoryToSave);

        return Response.builder()
                .status(200)
                .message("Category created successfully")
                .build();
    }

    @Override
    public Response updateCategory(CategoryDTO categoryDTO) {
        Category categoryToSave = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(categoryToSave);

        return Response.builder()
                .status(200)
                .message("Category Updated successfully")
                .build();
    }

    @Override
    public Response getAllCategories(int page, int size, String sortBy, String direction, String category, String refCode,String longName,String active) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Category> page1 = categoryRepository
                .findByCategoryContainingAndRefCodeContainingAndRefCodeLongNameContainingAndActiveContaining(
                        category != null ? category : "",
                        refCode != null ? refCode : "",
                        longName != null ? longName : "",
                        active != null ? active : "",
                        pageable
                );

        List<Category> categories = page1.getContent();
        List<CategoryDTO> categoryDTOS = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {}.getType());
        return Response.builder()
                .status(200)
                .message("success")
                .categories(categoryDTOS)
                .totalPages(page1.getTotalPages())
                .totalElements(page1.getTotalElements())
                .last(page1.isLast())
                .first(page1.isFirst())
                .empty(page1.isEmpty())
                .build();
    }

    @Override
    public Response getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Category Not Found"));
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return Response.builder()
                .status(200)
                .message("success")
                .category(categoryDTO)
                .build();
    }

    @Override
    public Response deleteCategory(Long id) {

         categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Category Not Found"));

        categoryRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("Category Successfully Deleted")
                .build();
    }

    @Override
    public List<String> getAllCategoryList() {
        System.out.println("getAllCategoryList1");
        List<Category> categoryList = categoryRepository.findAll();
        List<String> catList = categoryList.stream()
                .map(cat -> cat.getCategory()).distinct()
                .collect(Collectors.toList());

        return catList;
    }

    @Override
    public Map<String, String> getCategoryByCategory(String category) {
        System.out.println("getCategoryByCategory");
        List<Category> catList = categoryRepository.findByCategory(category);

        return catList.stream()
                .collect(Collectors.toMap(
                        Category::getRefCode,
                        Category::getRefCodeLongName
                ));

    }

    @Override
    public Map<String, Long> getCategoryCount() {
        List<Category> catList = categoryRepository.findAll();
        Map<String, Long> catCountMap = catList.stream()
                .collect(Collectors.groupingBy(
                        Category::getRefCodeLongName,   // key: the field
                        Collectors.counting()           // value: how many times it appears
                ));

        return catCountMap;
    }

    @Override
    public List<String> getSubCategory(String cat) {

       List<Category> catList = categoryRepository.findByCategory(cat);
        List<String> catList1 = catList.stream()
                .map(cat1 -> cat1.getSubCategory()).distinct()
                .collect(Collectors.toList());

        return catList1;
    }
}
