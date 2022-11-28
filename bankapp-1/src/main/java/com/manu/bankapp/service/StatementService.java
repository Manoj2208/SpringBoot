package com.manu.bankapp.service;

import java.time.LocalDate;
import java.util.List;

import com.manu.bankapp.dto.Statement;

public interface StatementService {
	List<Statement> transactionStatements(long accountNumber,LocalDate date1,LocalDate date2);

}
