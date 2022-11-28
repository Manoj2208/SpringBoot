package com.manu.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manu.bankapp.dto.Customer;
import com.manu.bankapp.dto.ResponseStructure;
import com.manu.bankapp.service.CustomerServiceImpl;

@RestController
public class CustomerController {
	@Autowired
	CustomerServiceImpl customerServiceImpl;

	@PostMapping("/customer")
	public ResponseStructure registerCustomer(@RequestBody Customer customer) {
		return customerServiceImpl.registerCustomer(customer);

	}

}
