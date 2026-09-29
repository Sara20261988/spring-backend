package com.example.springbackend.model;

public class AccountResponseDTO {

    private Long id;
    private String name;
    private AccountType type;

    public AccountResponseDTO(Long id, String name, AccountType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AccountType getType() {
        return type;
    }
}