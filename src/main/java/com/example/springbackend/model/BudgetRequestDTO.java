package com.example.springbackend.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.YearMonth;

public class BudgetRequestDTO {

    @Positive
    private BigDecimal amount;

    @NotNull
    private Long categoryId;

    @NotNull
    private YearMonth month;

    public BudgetRequestDTO() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }
}