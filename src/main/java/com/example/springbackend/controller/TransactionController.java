package com.example.springbackend.controller;

import com.example.springbackend.model.Transaction;
import org.springframework.web.bind.annotation.*;
import com.example.springbackend.service.TransactionService;
import java.util.List;
import jakarta.validation.Valid;
import com.example.springbackend.model.FinancialSummary;
import java.time.LocalDate;
import com.example.springbackend.model.TransactionType;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.springbackend.model.FinancialSummary;
import com.example.springbackend.model.TransactionResponseDTO;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@Valid @RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping
    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/type")
    public List<Transaction> getTransactionsByType(
            @RequestParam TransactionType type) {

        return transactionService.getTransactionsByType(type);
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(
            @PathVariable Long id,
            @Valid @RequestBody Transaction updatedTransaction) {

        return transactionService.updateTransaction(id, updatedTransaction);
    }

    @DeleteMapping("/{id}")
    public Transaction deleteTransaction(@PathVariable Long id) {
        return transactionService.deleteTransaction(id);
    }

    @GetMapping("/summary")
    public FinancialSummary getFinancialSummary() {
        return transactionService.getFinancialSummary();
    }

    @GetMapping("/summary/date")
    public FinancialSummary getFinancialSummaryByDate(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return transactionService.getFinancialSummaryByDate(
                startDate,
                endDate);
    }

    @GetMapping("/summary/month")
    public FinancialSummary getMonthlyFinancialSummary(
            @RequestParam int year,
            @RequestParam int month) {

        return transactionService.getMonthlyFinancialSummary(year, month);
    }
}