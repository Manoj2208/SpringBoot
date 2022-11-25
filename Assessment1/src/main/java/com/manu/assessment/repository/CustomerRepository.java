package com.hcl.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.assessment.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
