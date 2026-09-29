package com.example.springbackend.exception;

public class AccountInUseException extends RuntimeException {

    public AccountInUseException(Long id) {
        super("Cannot delete account with id " + id + " because it has transactions");
    }
}