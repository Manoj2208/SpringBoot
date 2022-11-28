package com.manu.bankapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manu.bankapp.dto.Statement;
import com.manu.bankapp.repository.StatementRepository;

@Service
public class StatementServiceImpl implements StatementService {
	@Autowired
	StatementRepository statementRepository;

	@Override
	public List<Statement> transactionStatements(long accountNumber, LocalDate date1, LocalDate date2) {
		return statementRepository.findByAccountNumberAndDateOfTransactionBetween(accountNumber, date1, date2);
	}

}
