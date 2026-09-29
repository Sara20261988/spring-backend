package com.example.springbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.YearMonth;
import jakarta.persistence.Convert;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"category_id", "month"})
        }
)
public class Budget {

    @Id
    @GeneratedValue
    private Long id;

    @Positive
    private BigDecimal amount;

    @NotNull
    @ManyToOne
    private Category category;

    @NotNull
    @Convert(converter = YearMonthAttributeConverter.class)
    private YearMonth month;

    public Budget() {
    }

    public Budget(Long id, BigDecimal amount, Category category, YearMonth month) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.month = month;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }
}