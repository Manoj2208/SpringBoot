package com.manu.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manu.bankapp.dto.Account;
import com.manu.bankapp.dto.ResponseStructure;
import com.manu.bankapp.dto.Statement;
import com.manu.bankapp.dto.Transaction;
import com.manu.bankapp.exception.AccountNotFoundException;
import com.manu.bankapp.repository.AccountRepository;
import com.manu.bankapp.repository.StatementRepository;
import com.manu.bankapp.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	StatementRepository statementRepository;

	@Override
	@Transactional
	public ResponseStructure transactionProcess(Transaction transaction) throws AccountNotFoundException {
		Account source = accountRepository.findByAccountNumber(transaction.getSourceAccountNo());
		Account target = accountRepository.findByAccountNumber(transaction.getTargetAccountNo());
		if (source != null && target != null) {
			if ((transaction.getAmount() > 0) && (transaction.getAmount() <= source.getAccountBalance())) {
				target.setAccountBalance(target.getAccountBalance() + transaction.getAmount());
				source.setAccountBalance(source.getAccountBalance() - transaction.getAmount());
				accountRepository.save(source);
				accountRepository.save(target);

				// setting status for transaction information to transaction entity
				String status = "An amount of INR " + transaction.getAmount() + " has been transferred from "
						+ source.getAccountNumber() + " to" + target.getAccountNumber();
				transaction.setStatus(status);
				transactionRepository.save(transaction);

				// setting status for debited info to statement entity
				String debitedStatus = "An amount of INR " + transaction.getAmount()
						+ "has been debited to your account " + transaction.getSourceAccountNo() + "(paid to):"
						+ target.getAccountNumber() + " available balance is:" + source.getAccountBalance();
				Statement statement = new Statement();
				statement.setAccountNumber(source.getAccountNumber());
				statement.setStatus(debitedStatus);
				statementRepository.save(statement);

				// setting status for credit info to statement entity
				String creditedStatus = "An amount of INR " + transaction.getAmount()
						+ "has been credited to your account " + target.getAccountNumber() + "from "
						+ source.getAccountNumber() + " available balance is:" + target.getAccountBalance();
				Statement statement1 = new Statement();
				statement1.setAccountNumber(target.getAccountNumber());
				statement1.setStatus(creditedStatus);
				statementRepository.save(statement1);

				ResponseStructure responseStructure = new ResponseStructure();
				responseStructure.setMessage("transaction sucessfull");
				return responseStructure;

			} else {
				ResponseStructure responseStructure = new ResponseStructure();
				responseStructure.setMessage("insufficient fund");
				return responseStructure;
			}

		} else {
			throw new AccountNotFoundException("incorrect Account number");
		}
	}

}
