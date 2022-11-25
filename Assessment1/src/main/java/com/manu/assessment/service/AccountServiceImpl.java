package com.hcl.assessment.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.assessment.dto.Account;
import com.hcl.assessment.dto.Customer;
import com.hcl.assessment.dto.Transaction;
import com.hcl.assessment.repository.AccountRepository;
import com.hcl.assessment.repository.CustomerRepository;
import com.hcl.assessment.repository.TransactionRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	Account a;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	Transaction transaction, transaction2;

	@Autowired
	TransactionRepository transcationRepository;

	@Override
	public String saveAccount(String accountType, double initialDeposit, Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
		a = new Account();
		long accountNumber = (long) (Math.random() * Math.pow(10, 10));
		// a.setAccountId(a.getAccountId());
		a.setAccountType(accountType);
		a.setDeposit(initialDeposit);
		a.setAccountNumber(accountNumber);
		a.setCustomerId(customer.getCustomerId());
		accountRepository.save(a);
		return "account with id:" + a.getAccountId() + "saved sucessfully";

	}

	@Override
	public String fundTransfer(long sourceAccno, long targetAccno, double amount) {
		// TODO Auto-generated method stub

		Account source = accountRepository.findByAccountNumber(sourceAccno);
		Account target = accountRepository.findByAccountNumber(targetAccno);
		if (source != null && target != null) {
			if (amount <= (source.getDeposit())) {
				target.setDeposit(target.getDeposit() + amount);
				source.setDeposit(source.getDeposit() - amount);
				accountRepository.save(source);
				accountRepository.save(target);

				// setting status for sourceaccount transactionstatus
				String status = "An amount of INR " + amount + "has been paid through your account "
						+ source.getAccountNumber() + " available balance is: " + source.getDeposit();

				// setting status for targetaccount transactionstatus
				String status1 = "An amount of INR " + amount + "has been credited to your account "
						+ target.getAccountNumber() + " available balance is: " + target.getDeposit();
				// saving transaction details into transaction table for sourceAccount
				transaction = new Transaction();
				transaction.setAccountNo(sourceAccno);
				transaction.setTransactionStatus(status);
				transcationRepository.save(transaction);

				// saving transaction details into transaction table for targetAccount
				transaction2 = new Transaction();
				transaction2.setAccountNo(targetAccno);
				transaction2.setTransactionStatus(status1);
				transcationRepository.save(transaction2);

				return "transaction successfull";

			} else {
				return " insufficient funds";
			}
		} else {
			return "invalid account number";
		}

	}

	@Override
	public List<Transaction> transactionHistory(long accountNo, LocalDate date) {
		// TODO Auto-generated method stub
		return transcationRepository.findByAccountNoAndDateTime(accountNo, date);
	}

}
