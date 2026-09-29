package com.example.springbackend.controller;

import com.example.springbackend.model.Budget;
import com.example.springbackend.service.BudgetService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import com.example.springbackend.model.BudgetSummaryDTO;
import com.example.springbackend.model.BudgetRequestDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public Budget createBudget(@Valid @RequestBody BudgetRequestDTO request) {
        return budgetService.createBudget(request);
    }

    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    @GetMapping("/{id}")
    public Budget getBudgetById(@PathVariable Long id) {
        return budgetService.getBudgetById(id);
    }

    @DeleteMapping("/{id}")
    public Budget deleteBudget(@PathVariable Long id) {
        return budgetService.deleteBudget(id);
    }

    @PutMapping("/{id}")
    public Budget updateBudget(
            @PathVariable Long id,
            @RequestBody Budget updatedBudget) {
        return budgetService.updateBudget(id, updatedBudget);
    }

    @GetMapping("/{id}/summary")
    public BudgetSummaryDTO getBudgetSummary(@PathVariable Long id) {
        return budgetService.getBudgetSummary(id);
    }
}