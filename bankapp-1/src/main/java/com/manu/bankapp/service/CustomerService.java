package com.manu.bankapp.service;

import com.manu.bankapp.dto.Customer;
import com.manu.bankapp.dto.ResponseStructure;

public interface CustomerService {
	ResponseStructure registerCustomer(Customer customer);

}
