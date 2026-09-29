package com.example.springbackend.model;

import java.math.BigDecimal;
import java.time.LocalDate;


public class TransactionResponseDTO {

    private Long id;
    private BigDecimal amount;
    private TransactionType type;
    private String category;
    private String account;
    private String description;
    private LocalDate date;

    public TransactionResponseDTO(
            Long id,
            BigDecimal amount,
            TransactionType type,
            String category,
            String account,
            String description,
            LocalDate date) {

        this.id = id;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.account = account;
        this.description = description;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public String getAccount() {
        return account;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}