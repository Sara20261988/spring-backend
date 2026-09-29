package com.example.springbackend.exception;

public class CategoryInUseException extends RuntimeException {

    public CategoryInUseException(Long id) {
        super("Cannot delete category with id " + id + " because it has transactions");
    }
}