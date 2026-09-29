package com.example.springbackend.service;

import com.example.springbackend.exception.UserNotFoundException;
import com.example.springbackend.model.Account;
import com.example.springbackend.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.springbackend.exception.AccountNotFoundException;

import com.example.springbackend.model.Transaction;
import com.example.springbackend.repository.TransactionRepository;

import com.example.springbackend.model.TransactionType;
import java.math.BigDecimal;

import com.example.springbackend.model.FinancialSummary;
import com.example.springbackend.model.Transaction;
import com.example.springbackend.model.TransactionType;
import com.example.springbackend.model.AccountResponseDTO;
import com.example.springbackend.exception.AccountInUseException;
import com.example.springbackend.model.AccountRequestDTO;
import com.example.springbackend.model.User;
import com.example.springbackend.repository.UserRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public AccountService(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository,
            UserRepository userRepository){

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }


    public Account createAccount(AccountRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User with id " + request.getUserId() + " was not found"));

        Account account = new Account();
        account.setName(request.getName());
        account.setType(request.getType());
        account.setUser(user);

        return accountRepository.save(account);
    }

    public List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public AccountResponseDTO getAccountById(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        return toResponseDTO(account);
    }

    public Account updateAccount(Long id, AccountRequestDTO request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException(
                        "User with id " + request.getUserId() + " was not found"));

        account.setName(request.getName());
        account.setType(request.getType());
        account.setUser(user);

        return accountRepository.save(account);
    }

    public Account deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        if (transactionRepository.existsByAccountId(id)) {
            throw new AccountInUseException(id);
        }

        accountRepository.delete(account);
        return account;
    }

    public BigDecimal getAccountBalance(Long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        BigDecimal balance = transactionRepository.findByAccountId(accountId)
                .stream()
                .filter(transaction -> transaction.getAccount().getId().equals(account.getId()))
                .map(transaction -> {
                    if (transaction.getType() == TransactionType.INCOME) {
                        return transaction.getAmount();
                    } else {
                        return transaction.getAmount().negate();
                    }
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return balance;
    }

    public FinancialSummary getAccountFinancialSummary(Long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        List<Transaction> transactions =
                transactionRepository.findByAccountId(account.getId());

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

    private AccountResponseDTO toResponseDTO(Account account) {

        return new AccountResponseDTO(
                account.getId(),
                account.getName(),
                account.getType()
        );
    }

   }