package com.manu.bankapp.util;

import java.util.regex.Pattern;

import com.manu.bankapp.dto.Customer;
import com.manu.bankapp.dto.ResponseStructure;

public class CustomerValidation {
	public ResponseStructure validateCustomer(Customer customer) {
		ResponseStructure responseStructure = new ResponseStructure();
		if (!Pattern.matches("[A-Z]{1}[a-z]*", customer.getFirstName())) {
			responseStructure.setMessage("name is invalid");
			return responseStructure;
		}
		if (!Pattern.matches("[a-zA-Z0-9._-]+@[a-zA-Z]+.[a-zA-Z]{2,4}[.]{0,1}[a-zA-Z]{0,2}", customer.getEmail())) {
			responseStructure.setMessage("email is invlid");
			return responseStructure;
		}
		if (!(customer.getAge() >= 18)) {
			responseStructure.setMessage("age must be 18 or more than 18");
			return responseStructure;
		}
		if (!Pattern.matches("[6-9][0-9]{9}", Long.toString(customer.getPhoneNumber()))) {
			responseStructure.setMessage("phone number is invalid");
			return responseStructure;
		}
		if (!Pattern.matches("[A-Z]{1}[a-z]*", customer.getFatherName())) {
			responseStructure.setMessage("father name is invalid format");
			return responseStructure;
		}
		if (!Pattern.matches("^[2-9]{1}[0-9]{11}$", Long.toString(customer.getAadharNo()))) {
			responseStructure.setMessage("Aadhar number is invalid");
			return responseStructure;
		}
		if (!(customer.getAccount().getAccountBalance() >= 0)) {
			responseStructure.setMessage("amount must be greater than or equal to zero");
			return responseStructure;
		}
		if (!(customer.getAccount().getAccountType().equalsIgnoreCase("Savings")
				|| customer.getAccount().getAccountType().equalsIgnoreCase("Current"))) {
			responseStructure.setMessage("Account type must be savings or current");
			return responseStructure;
		}
		responseStructure.setMessage("true");
		return responseStructure;
	}

}
