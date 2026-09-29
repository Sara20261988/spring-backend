package com.example.springbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.ManyToOne;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private AccountType type;

    @ManyToOne
    private User user;

    public Account() {
    }

    public Account(Long id, String name, AccountType type, User user) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}