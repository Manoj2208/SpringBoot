package com.manu.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manu.bankapp.dto.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findByAccountNumber(long accountNumber);

}
