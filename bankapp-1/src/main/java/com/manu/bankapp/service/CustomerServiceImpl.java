package com.manu.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manu.bankapp.dto.Customer;
import com.manu.bankapp.dto.ResponseStructure;
import com.manu.bankapp.repository.CustomerRepository;
import com.manu.bankapp.util.CustomerValidation;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public ResponseStructure registerCustomer(Customer customer) {
		CustomerValidation customerValidation = new CustomerValidation();
		ResponseStructure responseStructure = new ResponseStructure();
		responseStructure = customerValidation.validateCustomer(customer);
		if (responseStructure.getMessage().equals("true")) {
			long accountNumber = (long) (Math.random() * Math.pow(10, 10));
			customer.getAccount().setAccountNumber(accountNumber);
			customer.getAccount().setBankName("canara bank");
			customerRepository.save(customer);
			responseStructure.setMessage("account for customer registered sucessfully");
			return responseStructure;
		} else {
			return responseStructure;
		}

	}

}
