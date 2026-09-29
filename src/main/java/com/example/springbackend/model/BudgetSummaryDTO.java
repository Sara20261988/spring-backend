package com.example.springbackend.model;

import java.math.BigDecimal;

public class BudgetSummaryDTO {

    private String category;
    private BigDecimal budgetAmount;
    private BigDecimal actualSpending;
    private BigDecimal remaining;

    public BudgetSummaryDTO(
            String category,
            BigDecimal budgetAmount,
            BigDecimal actualSpending,
            BigDecimal remaining) {

        this.category = category;
        this.budgetAmount = budgetAmount;
        this.actualSpending = actualSpending;
        this.remaining = remaining;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }

    public BigDecimal getActualSpending() {
        return actualSpending;
    }

    public BigDecimal getRemaining() {
        return remaining;
    }
}