package com.manu.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manu.bankapp.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
