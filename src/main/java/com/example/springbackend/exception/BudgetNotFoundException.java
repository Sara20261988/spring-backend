package com.example.springbackend.exception;

public class BudgetNotFoundException extends RuntimeException {

    public BudgetNotFoundException(Long id) {
        super("Budget with id " + id + " was not found");
    }
}