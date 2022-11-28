package com.manu.bankapp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manu.bankapp.dto.Statement;
import com.manu.bankapp.service.StatementServiceImpl;

@RestController
public class StatementController {
	@Autowired
	StatementServiceImpl statementServiceImpl;

	@GetMapping("/statements")
	public List<Statement> transactionStatement(@RequestParam long accountNumber, String date) {
		LocalDate date1 = LocalDate.parse(date + "-01", DateTimeFormatter.ofPattern("yyyy-MMM-d", Locale.ENGLISH));
		LocalDate date2 = LocalDate.parse(date + "-30", DateTimeFormatter.ofPattern("yyyy-MMM-d", Locale.ENGLISH));
		return statementServiceImpl.transactionStatements(accountNumber, date1, date2);

	}

}
