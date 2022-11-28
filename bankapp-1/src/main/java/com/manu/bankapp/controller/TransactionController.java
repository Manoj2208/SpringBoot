package com.manu.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manu.bankapp.dto.ResponseStructure;
import com.manu.bankapp.dto.Transaction;
import com.manu.bankapp.exception.AccountNotFoundException;
import com.manu.bankapp.service.TransactionServiceImpl;

@RestController
public class TransactionController {
	@Autowired
	TransactionServiceImpl transactionServiceImpl;
	
	@PostMapping("/transaction")
	public ResponseStructure fundTransfer(@RequestBody Transaction transaction) throws AccountNotFoundException {
		return transactionServiceImpl.transactionProcess(transaction);
	}

}
