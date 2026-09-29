package com.example.springbackend.model;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;



@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @Positive
    private BigDecimal amount;

    @NotNull
    private TransactionType type;

    @Size(max = 255)
    private String description;

    @ManyToOne
    @NotNull
    private Account account;

    @NotNull
    @ManyToOne
    private Category category;

    @NotNull
    private LocalDate date;

    public Transaction() {
    }

    public Transaction(Long id, BigDecimal amount, TransactionType type,
                       Category category, String description, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
