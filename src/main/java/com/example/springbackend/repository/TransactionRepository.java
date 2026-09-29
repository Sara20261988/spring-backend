package com.example.springbackend.repository;

import com.example.springbackend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springbackend.model.TransactionType;
import java.time.LocalDate;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByAccountId(Long accountId);
    List<Transaction> findByCategoryId(Long categoryId);
    List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);
    boolean existsByAccountId(Long accountId);
    boolean existsByCategoryId(Long categoryId);
}

