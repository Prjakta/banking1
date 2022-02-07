package com.banking.auth.service;

import com.banking.auth.entities.AccountDetails;
import com.banking.auth.entities.Customers;

public interface CustomerService {
	Customers registerCustomer(Customers customer);

	Customers findCustomerByEmail(String email);

	AccountDetails addAccountDetails(AccountDetails accountDetails);

	Customers findById(long customerId);

	int updateCustomer(String firstName, String middleName, String lastName, String address, long customerId);

	int updatePassword(String confirmPassword, String email);

	

}
