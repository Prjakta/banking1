package com.banking.auth.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.auth.entities.AccountDetails;
import com.banking.auth.repository.AccountDetailsRepository;
import com.banking.auth.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountDetailsRepository accountDetailsRepository;

	
	@Override
	public AccountDetails fetchAccountDetails(String accountDetails) {
		// TODO Auto-generated method stub
		return accountDetailsRepository.fetchAccountDetails(accountDetails);
	}



	@Override
	public int updateAccountBalance(long accountBalance,String date, String accountNumber) {
		// TODO Auto-generated method stub
		return accountDetailsRepository.updateAccountBalance(accountBalance,date,accountNumber);
	}


	
}
