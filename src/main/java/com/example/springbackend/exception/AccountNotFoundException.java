package com.example.springbackend.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long id) {
        super("Account with id " + id + " was not found");
    }
}