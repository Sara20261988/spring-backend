package com.example.springbackend.controller;

import com.example.springbackend.model.Account;
import com.example.springbackend.model.AccountRequestDTO;
import com.example.springbackend.service.AccountService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import jakarta.validation.Valid;
import com.example.springbackend.model.FinancialSummary;
import com.example.springbackend.model.AccountResponseDTO;
import java.util.List;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(
            @Valid @RequestBody AccountRequestDTO request) {
        return accountService.createAccount(request);
    }

    @GetMapping
    public List<AccountResponseDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountResponseDTO getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }


    @PutMapping("/{id}")
    public Account updateAccount(
            @PathVariable Long id,
            @Valid @RequestBody AccountRequestDTO request) {
        return accountService.updateAccount(id, request);
    }


    @DeleteMapping("/{id}")
    public Account deleteAccount(@PathVariable Long id) {
        return accountService.deleteAccount(id);
    }

    @GetMapping("/{id}/balance")
    public BigDecimal getAccountBalance(@PathVariable Long id) {
        return accountService.getAccountBalance(id);
    }

    @GetMapping("/{id}/summary")
    public FinancialSummary getAccountFinancialSummary(@PathVariable Long id) {
        return accountService.getAccountFinancialSummary(id);
    }


}