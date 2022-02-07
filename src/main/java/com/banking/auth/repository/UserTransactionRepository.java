package com.banking.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.banking.auth.entities.AccountDetails;
import com.banking.auth.entities.UserTransactionsDetails;
@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransactionsDetails,Long> {

	//UserTransactionsDetails saveTransactionLog(UserTransactionsDetails senderTransactionsDetails);
	

}
