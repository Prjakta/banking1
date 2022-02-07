package com.banking.auth.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banking.auth.entities.AccountDetails;
import com.banking.auth.entities.Customers;
import com.banking.auth.repository.AccountDetailsRepository;
import com.banking.auth.repository.CustomerRepository;
import com.banking.auth.service.CustomerService;
import com.banking.auth.util.AccountNumberGenarator;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	AccountDetailsRepository accountDetailsRepository;
	@Autowired
	AccountNumberGenarator accountNumberGenarator;
	PasswordEncoder passwordEncoder;

	@Override
	public Customers registerCustomer(Customers customers) {
		
		// TODO Auto-generated method stub
		//this.passwordEncoder = new BCryptPasswordEncoder();
		//String encodedPassword = this.passwordEncoder.encode());
		customers.setPassword(customers.getPassword());
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		String accountNumber = accountNumberGenarator.generateAccountNumber()+""+accountNumberGenarator.generateAccountNumber();
		String currentTime = dateFormat.format(new Date());
		customers.setAccountNumber(accountNumber);
		customers.setTransactionPin(""+accountNumberGenarator.generateTransactionPin());
		customers.setStatus("1");
		customers.setCreatedAt(currentTime);
		customers.setUpdatedAt(currentTime);
		return customerRepository.save(customers);
	}
	@Override
	public Customers findCustomerByEmail(String email) {
		// TODO Auto-generated method stub
		return customerRepository.findCustomerByEmail(email) ;
	}
	@Override
	public AccountDetails addAccountDetails(AccountDetails accountDetails) {
		// TODO Auto-generated method stub
		return accountDetailsRepository.save(accountDetails) ;
	}
	
	@Override
	public Customers findById(long customerId) {
		// TODO Auto-generated method stub
		return customerRepository.findById(customerId);
	}
	@Override
	public int updateCustomer(String firstName, String middleName, String lastName, String address, long customerId) {
		// TODO Auto-generated method stub
		return customerRepository.updateCustomer(firstName,middleName,lastName,address,customerId);
	}
	@Override
	public int updatePassword(String confirmPassword, String email) {
		// TODO Auto-generated method stub
		return customerRepository.updatePassword(confirmPassword,email);
	}
	

}
