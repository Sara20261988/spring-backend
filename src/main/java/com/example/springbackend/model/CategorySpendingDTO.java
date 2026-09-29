package com.example.springbackend.model;

import java.math.BigDecimal;

public class CategorySpendingDTO {

    private String category;
    private BigDecimal amount;

    public CategorySpendingDTO(String category, BigDecimal amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}