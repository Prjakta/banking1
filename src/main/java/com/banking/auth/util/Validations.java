package com.banking.auth.util;

import org.springframework.stereotype.Service;

import com.banking.auth.entities.AccountDetails;
import com.banking.auth.entities.Customers;
import com.banking.auth.exception.InvalidRequestException;
@Service
public class Validations {

	public void registerCustomer(Customers customers) {
		// TODO Auto-generated method stub
		if(customers.getFirstName().equals("")) {
			throw new InvalidRequestException("First name should not be null");
		}
		String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if(customers.getEmail().matches(emailRegex)== false) {
			throw new InvalidRequestException("Email address should be in correct format");

			
		}
	     String  mobilePhnRegex = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$";
	     if(customers.getMobile().matches(mobilePhnRegex) ==false) {
				throw new InvalidRequestException("Mobile Number should be in correct format");

	    	 
	     }
	     String adharRegex = "^[2-9][0-9]{11}$";
	     if(customers.getAadharNumber().matches(adharRegex)==false) {
				throw new InvalidRequestException("Adhar Number should be in correct format");

	    	 
	     }

		
	}

	public void loginCustomer(Customers customers) {
		// TODO Auto-generated method stub
	
	String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	if(customers.getEmail().matches(emailRegex)== false) {
		throw new InvalidRequestException("Email address should be in correct format");

		
	}
	if(customers.getEmail().equals("")) {
		throw new InvalidRequestException("Email address should not be empty");

	}
	if(customers.getPassword().equals("")) {
		throw new InvalidRequestException("Password should not be empty");

	}

		
		
	}

	public void addBalanceValidations(AccountDetails accountDetails) {
		// TODO Auto-generated method stub
		if(accountDetails.getAccountNumber().equals("")) {
			throw new InvalidRequestException("Account Number should not be empty");
	
		}
		if(accountDetails.getAccountBalance()<= 0) {
			throw new InvalidRequestException("Account Balance should not be empty");
	
		}
		
	}

}
