package com.example.springbackend.controller;

import com.example.springbackend.model.Category;
import com.example.springbackend.model.CategoryResponseDTO;
import com.example.springbackend.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.Valid;
import com.example.springbackend.model.CategorySpendingDTO;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO  getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody Category updatedCategory) {

        return categoryService.updateCategory(id, updatedCategory);
    }

    @DeleteMapping("/{id}")
    public Category deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/{id}/spending")
    public BigDecimal getCategorySpending(@PathVariable Long id) {
        return categoryService.getCategorySpending(id);
    }

    @GetMapping("/spending")
    public List<CategorySpendingDTO> getAllCategorySpending() {
        return categoryService.getAllCategorySpending();
    }
}