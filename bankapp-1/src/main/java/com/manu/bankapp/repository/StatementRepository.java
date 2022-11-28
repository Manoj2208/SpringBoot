package com.manu.bankapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manu.bankapp.dto.Statement;

public interface StatementRepository extends JpaRepository<Statement, Integer> {
	List<Statement> findByAccountNumberAndDateOfTransactionBetween(long accountNumber, LocalDate date1,
			LocalDate date2);

}
