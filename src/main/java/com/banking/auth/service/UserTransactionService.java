package com.banking.auth.service;

import com.banking.auth.entities.UserTransactionsDetails;

public interface UserTransactionService {

	UserTransactionsDetails saveTransactionLog(UserTransactionsDetails senderTransactionsDetails);


}
