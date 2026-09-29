package com.example.springbackend.service;

import com.example.springbackend.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.springbackend.repository.TransactionRepository;

import com.example.springbackend.exception.TransactionNotFoundException;
import com.example.springbackend.model.FinancialSummary;
import com.example.springbackend.model.TransactionType;
import java.math.BigDecimal;
import java.time.YearMonth;

import com.example.springbackend.model.TransactionType;
import com.example.springbackend.repository.AccountRepository;
import com.example.springbackend.model.Account;
import com.example.springbackend.exception.AccountNotFoundException;
import com.example.springbackend.repository.CategoryRepository;
import com.example.springbackend.model.Category;
import com.example.springbackend.exception.CategoryNotFoundException;
import java.time.LocalDate;
import com.example.springbackend.model.TransactionResponseDTO;


@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;



    public TransactionService(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository,
            CategoryRepository categoryRepository) {

        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    public Transaction createTransaction(Transaction transaction) {

        Account account = accountRepository.findById(transaction.getAccount().getId())
                .orElseThrow(() -> new AccountNotFoundException(
                        transaction.getAccount().getId()));

        Category category = categoryRepository.findById(transaction.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        transaction.getCategory().getId()));

        transaction.setAccount(account);
        transaction.setCategory(category);

        return transactionRepository.save(transaction);
    }

    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<Transaction> getTransactionsByType(TransactionType type) {
        return transactionRepository.findByType(type);
    }

    public TransactionResponseDTO getTransactionById(Long id) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        return toResponseDTO(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setType(updatedTransaction.getType());
        transaction.setDate(updatedTransaction.getDate());

        Category category = categoryRepository.findById(
                updatedTransaction.getCategory().getId()
        ).orElseThrow(() -> new CategoryNotFoundException(
                updatedTransaction.getCategory().getId()
        ));

        transaction.setCategory(category);
        transaction.setDescription(updatedTransaction.getDescription());
        Account account = accountRepository.findById(updatedTransaction.getAccount().getId())
                .orElseThrow(() -> new AccountNotFoundException(
                        updatedTransaction.getAccount().getId()));

        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }

    public Transaction deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        transactionRepository.delete(transaction);

        return transaction;
    }

    public FinancialSummary getFinancialSummary() {

        BigDecimal totalIncome = transactionRepository.findAll()
                .stream()
                .filter(transaction -> transaction.getType() == TransactionType.INCOME)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = transactionRepository.findAll()
                .stream()
                .filter(transaction -> transaction.getType() == TransactionType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpenses);

        return new FinancialSummary(totalIncome, totalExpenses, balance);
    }

    public FinancialSummary getFinancialSummaryByDate(
            LocalDate startDate,
            LocalDate endDate) {

        List<Transaction> transactions =
                transactionRepository.findByDateBetween(startDate, endDate);

        BigDecimal totalIncome = transactions.stream()
                .filter(transaction -> transaction.getType() == TransactionType.INCOME)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = transactions.stream()
                .filter(transaction -> transaction.getType() == TransactionType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpenses);

        return new FinancialSummary(
                totalIncome,
                totalExpenses,
                balance);
    }

    public FinancialSummary getMonthlyFinancialSummary(int year, int month) {

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        return getFinancialSummaryByDate(startDate, endDate);
    }

    private TransactionResponseDTO toResponseDTO(Transaction transaction) {

        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getCategory().getName(),
                transaction.getAccount().getName(),
                transaction.getDescription(),
                transaction.getDate()
        );
    }
}