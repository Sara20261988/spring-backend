package com.example.springbackend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountRequestDTO {

    @NotBlank
    private String name;

    @NotNull
    private AccountType type;

    @NotNull
    private Long userId;

    public AccountRequestDTO() {}

    public String getName() {
        return name;
    }

    public AccountType getType() {
        return type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}