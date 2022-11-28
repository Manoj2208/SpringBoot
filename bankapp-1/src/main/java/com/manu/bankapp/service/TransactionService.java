package com.manu.bankapp.service;

import com.manu.bankapp.dto.ResponseStructure;
import com.manu.bankapp.dto.Transaction;
import com.manu.bankapp.exception.AccountNotFoundException;

public interface TransactionService {
	ResponseStructure transactionProcess(Transaction transaction) throws AccountNotFoundException;

}
