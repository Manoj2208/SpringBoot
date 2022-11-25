package com.hcl.assessment.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.assessment.dto.Customer;
import com.hcl.assessment.dto.Transaction;
import com.hcl.assessment.service.AccountServiceImpl;

@RestController
public class AccountController {
	@Autowired
	AccountServiceImpl accountServiceImpl;

	@PostMapping("/saveaccount")
	public void saveAccount(@RequestParam String accountType, double initialDeposit, @RequestBody Customer customer) {
		accountServiceImpl.saveAccount(accountType, initialDeposit, customer);

	}

	@PostMapping("/fundtransfer")
	public String fundTransfer(@RequestParam long sourceAccno, long targetAccno, double amount) {
		return accountServiceImpl.fundTransfer(sourceAccno, targetAccno, amount);
	}

	@GetMapping("/transactionstatus")
	public List<Transaction> transactionStatus(@RequestParam long accountNumber, String date) {
		LocalDate today = LocalDate.parse(date);
		return accountServiceImpl.transactionHistory(accountNumber, today);
	}

}
