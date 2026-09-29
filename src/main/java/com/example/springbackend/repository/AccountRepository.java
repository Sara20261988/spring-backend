package com.example.springbackend.repository;

import com.example.springbackend.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AccountRepository extends JpaRepository<Account, Long> {

}


