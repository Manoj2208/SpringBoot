package com.manu.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manu.bankapp.dto.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
