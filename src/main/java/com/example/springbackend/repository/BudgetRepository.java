package com.example.springbackend.repository;

import com.example.springbackend.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.YearMonth;
import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByMonth(YearMonth month);

    boolean existsByCategoryIdAndMonth(Long categoryId, YearMonth month);
}
