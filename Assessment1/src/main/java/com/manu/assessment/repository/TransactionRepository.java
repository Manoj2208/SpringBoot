package com.hcl.assessment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.assessment.dto.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByAccountNoAndDateTime(long accountNo,LocalDate date);

}
