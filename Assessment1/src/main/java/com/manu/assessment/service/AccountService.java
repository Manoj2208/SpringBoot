package com.hcl.assessment.service;

import java.time.LocalDate;
import java.util.List;

import com.hcl.assessment.dto.Customer;
import com.hcl.assessment.dto.Transaction;

public interface AccountService {

	String saveAccount(String accountType, double initialDeposit,Customer customer);
	
	String fundTransfer(long sourceAccno,long targetAccno,double amount);
	
	List<Transaction> transactionHistory(long accountNo,LocalDate date);

}
