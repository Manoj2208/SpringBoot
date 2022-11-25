package com.hcl.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.assessment.dto.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	//boolean findByAccountNumber(long accountNumber);
	
	Account findByAccountNumber(long accountNumber);
	

}
