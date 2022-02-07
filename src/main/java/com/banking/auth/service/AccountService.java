package com.banking.auth.service;

import com.banking.auth.entities.AccountDetails;

public interface AccountService {

	AccountDetails fetchAccountDetails(String accountDetails);

	
	int updateAccountBalance(long accountBalance,String date,String accountNumber);

	
}
