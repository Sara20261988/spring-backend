package com.example.springbackend.service;

import com.example.springbackend.model.*;
import com.example.springbackend.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import com.example.springbackend.exception.BudgetNotFoundException;
import com.example.springbackend.repository.CategoryRepository;
import com.example.springbackend.exception.CategoryNotFoundException;
import com.example.springbackend.repository.TransactionRepository;
import com.example.springbackend.exception.BudgetAlreadyExistsException;

import java.time.YearMonth;


@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    public BudgetService(
            BudgetRepository budgetRepository,
            CategoryRepository categoryRepository,
            TransactionRepository transactionRepository) {

        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
        this.transactionRepository =  transactionRepository;
    }

    public Budget createBudget(BudgetRequestDTO request) {

        if (budgetRepository.existsByCategoryIdAndMonth(
                request.getCategoryId(),
                request.getMonth())) {

            throw new BudgetAlreadyExistsException();
        }

        Category category = categoryRepository.findById(
                request.getCategoryId()
        ).orElseThrow(() -> new CategoryNotFoundException(
                request.getCategoryId()
        ));

        Budget budget = new Budget();

        budget.setAmount(request.getAmount());
        budget.setCategory(category);
        budget.setMonth(request.getMonth());

        return budgetRepository.save(budget);
    }
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException(id));
    }

    public Budget deleteBudget(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException(id));

        budgetRepository.delete(budget);

        return budget;
    }

    public Budget updateBudget(Long id, Budget updatedBudget) {

        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException(id));

        Category category = categoryRepository.findById(
                updatedBudget.getCategory().getId()
        ).orElseThrow(() -> new CategoryNotFoundException(
                updatedBudget.getCategory().getId()
        ));

        budget.setAmount(updatedBudget.getAmount());
        budget.setCategory(category);
        budget.setMonth(updatedBudget.getMonth());

        return budgetRepository.save(budget);
    }

    public BudgetSummaryDTO getBudgetSummary(Long budgetId) {

        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new BudgetNotFoundException(budgetId));

        BigDecimal actualSpending = transactionRepository
                .findByCategoryId(budget.getCategory().getId())
                .stream()
                .filter(transaction ->
                        transaction.getType() == TransactionType.EXPENSE)
                .filter(transaction ->
                        YearMonth.from(transaction.getDate())
                                .equals(budget.getMonth()))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal remaining =
                budget.getAmount().subtract(actualSpending);

        return new BudgetSummaryDTO(
                budget.getCategory().getName(),
                budget.getAmount(),
                actualSpending,
                remaining
        );
    }
}