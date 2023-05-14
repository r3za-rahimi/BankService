package com.asan.bank.repositories;

import com.asan.bank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction , Long> {
}
