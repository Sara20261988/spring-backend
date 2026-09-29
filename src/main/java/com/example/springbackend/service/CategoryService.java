package com.example.springbackend.service;

import com.example.springbackend.model.*;
import com.example.springbackend.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import com.example.springbackend.exception.CategoryNotFoundException;
import com.example.springbackend.repository.TransactionRepository;
import com.example.springbackend.exception.CategoryInUseException;

import java.math.BigDecimal;
import java.math.BigDecimal;

import java.util.List;
import java.util.ArrayList;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    public CategoryService(
            CategoryRepository categoryRepository,
            TransactionRepository transactionRepository) {

        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public CategoryResponseDTO getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        return toResponseDTO(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        category.setName(updatedCategory.getName());

        return categoryRepository.save(category);
    }

    public Category deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        if (transactionRepository.existsByCategoryId(id)) {
            throw new CategoryInUseException(id);
        }

        categoryRepository.delete(category);
        return category;
    }

    public BigDecimal getCategorySpending(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        return transactionRepository.findByCategoryId(categoryId)
                .stream()
                .filter(transaction -> transaction.getType() == TransactionType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<CategorySpendingDTO> getAllCategorySpending() {

        List<CategorySpendingDTO> result = new ArrayList<>();

        for (Category category : categoryRepository.findAll()) {

            BigDecimal spending = transactionRepository
                    .findByCategoryId(category.getId())
                    .stream()
                    .filter(transaction -> transaction.getType() == TransactionType.EXPENSE)
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            result.add(
                    new CategorySpendingDTO(
                            category.getName(),
                            spending
                    )
            );
        }

        return result;
    }

    private CategoryResponseDTO toResponseDTO(Category category) {

        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }
}