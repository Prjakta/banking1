package com.banking.auth.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.auth.entities.UserTransactionsDetails;
import com.banking.auth.repository.UserTransactionRepository;
import com.banking.auth.service.UserTransactionService;

@Service
public class UserTransactionServiceImpl implements UserTransactionService {
	@Autowired
	UserTransactionRepository userTransactionRepository;

	@Override
	public UserTransactionsDetails saveTransactionLog(UserTransactionsDetails senderTransactionsDetails) {
		// TODO Auto-generated method stub
		return userTransactionRepository.save(senderTransactionsDetails);
	}
	

}
