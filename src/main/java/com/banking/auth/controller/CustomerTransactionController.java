package com.banking.auth.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.auth.CustomerResponseForRegistration.CustomerResponseForAccountDetails;
import com.banking.auth.CustomerResponseForRegistration.CustomerResponseForNoUser;
//import com.banking.auth.CustomerResponseForRegistration.CustomerResponseForRegister;
import com.banking.auth.customerRequest.CustomerRequestForMoneyTransfer;
import com.banking.auth.customerRequest.UpdatePassword;
import com.banking.auth.entities.AccountDetails;
import com.banking.auth.entities.Customers;
import com.banking.auth.entities.UserTransactionsDetails;
import com.banking.auth.service.AccountService;
import com.banking.auth.service.CustomerService;
import com.banking.auth.service.UserTransactionService;
import com.banking.auth.util.Validations;

@RestController
@RequestMapping("/customer/transaction")

public class CustomerTransactionController {
	
	@Autowired
	AccountService accountService; 
	@Autowired
	Validations validations;
	@Autowired
	CustomerRequestForMoneyTransfer transferMoney;
	@Autowired
	UserTransactionService userTransactionService;
	
	@Autowired
	CustomerService customerService;
	

	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	@PostMapping("/addBalance")
	public ResponseEntity<Object> addBalance(@RequestBody AccountDetails accountDetails){
		
		validations.addBalanceValidations(accountDetails); 

		AccountDetails fetchAccountDetails = accountService.fetchAccountDetails(accountDetails.getAccountNumber());
		if(fetchAccountDetails!= null) {
			long accountBalance = fetchAccountDetails.getAccountBalance() + accountDetails.getAccountBalance();
			String date = dateFormat.format(new Date());

			accountService.updateAccountBalance(accountBalance,date,accountDetails.getAccountNumber());
			CustomerResponseForAccountDetails responseStructure = new CustomerResponseForAccountDetails(new Date(),"Account Balance Updated..!","200", accountDetails);
			return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
		}else {
			CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Customer Already Registered with the same email..!","409");
			return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
	
		}
		
	}
	@PostMapping("/transferMoney")
	public ResponseEntity<Object> moneyTransfer(@RequestBody CustomerRequestForMoneyTransfer moneyTransfer){
		//AccountDetails accountDetails = new AccountDetails();
		//AccountDetails fetchAccountDetails = accountService.fetchAccountDetails(accountDetails.getAccountNumber());
		AccountDetails fetchSenderAccountDetails = accountService.fetchAccountDetails(moneyTransfer.getAccountNumber());
		if(fetchSenderAccountDetails!= null) {
			
			AccountDetails fetchReceiverAccountDetails = accountService.fetchAccountDetails(moneyTransfer.getUserAccountNumber());
			if(fetchReceiverAccountDetails!= null) {
				
				if(moneyTransfer.getBranchName().equals(fetchSenderAccountDetails.getBranchName())) {
					if(moneyTransfer.getIfsc().equals(fetchReceiverAccountDetails.getIfsc())) {
					String date = dateFormat.format(new Date());
					long senderAccountBalance = fetchSenderAccountDetails.getAccountBalance() - moneyTransfer.getTransferAmount();
					accountService.updateAccountBalance(senderAccountBalance,date,fetchSenderAccountDetails.getAccountNumber());
					
					UserTransactionsDetails senderTransactionsDetails = new UserTransactionsDetails();
					senderTransactionsDetails.setAccountNumber(fetchSenderAccountDetails.getAccountNumber());
					senderTransactionsDetails.setUserAccountNumber(fetchReceiverAccountDetails.getAccountNumber());
					senderTransactionsDetails.setTransferAmount(moneyTransfer.getTransferAmount());
					senderTransactionsDetails.setCreatedAt(date);
					senderTransactionsDetails.setUpdatedAt(date);
					senderTransactionsDetails.setStatus("Debited");
					userTransactionService.saveTransactionLog(senderTransactionsDetails);
					
					long receiverAccountBalance = fetchReceiverAccountDetails.getAccountBalance() + moneyTransfer.getTransferAmount();
					accountService.updateAccountBalance(receiverAccountBalance,date,fetchReceiverAccountDetails.getAccountNumber());
					

					UserTransactionsDetails recieverTransactionsDetails = new UserTransactionsDetails();
					recieverTransactionsDetails.setAccountNumber(fetchReceiverAccountDetails.getAccountNumber());
					recieverTransactionsDetails.setUserAccountNumber(fetchSenderAccountDetails.getAccountNumber());
					recieverTransactionsDetails.setTransferAmount(moneyTransfer.getTransferAmount());
					recieverTransactionsDetails.setCreatedAt(date);
					recieverTransactionsDetails.setUpdatedAt(date);
					recieverTransactionsDetails.setStatus("Credited");
					userTransactionService.saveTransactionLog(recieverTransactionsDetails);

					
					CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Money Transfered successfully..!","200");
					return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
					}else {
						CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Receivers ifsc not Matched..!","409");
						return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
						
						
					}
					
					
					
				}else {
					CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Receivers Brance not Matched..!","409");
					return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
				}
				
		}else {
			CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Receivers Account Not Found..!","409");
			return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
		}
	}else {
		CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Receivers Account Not Found..!","409");
		return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
	
	}
	
	}
	@PostMapping("/updateCustomer")
	public ResponseEntity<Object> updateCustomer(@RequestBody Customers customers){
		Customers customerUpdate = customerService.findById(customers.getCustomerId());
		if(customerUpdate!= null) {
			customerService.updateCustomer(customers.getFirstName(),customers.getMiddleName(),customers.getLastName(),customers.getAddress(),customers.getCustomerId());
		
		CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Customer Upadates Successfully..!","409");
		return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
	}else{
		CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Customer Not Found..!","409");
		return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
		
	}
	}
	
	@PostMapping("/updatePassword")
	public ResponseEntity<Object> updatePassword(@RequestBody UpdatePassword updatePassword){
		 
		Customers findCustomer = customerService.findCustomerByEmail(updatePassword.getEmail());
		if(findCustomer!= null) {
			if(updatePassword.getNewPassword().equals(updatePassword.getConfirmPassword())) {
			if(updatePassword.getNewPassword().equals(findCustomer.getPassword())) {
				CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Password should not be same as old password...!","409");
				return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
			}else {
				customerService.updatePassword(updatePassword.getConfirmPassword(),updatePassword.getEmail());
				CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Password Upadated Successfully..!","409");
				return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
			}
				
			}else {
				CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Confirm password not matched to new password..!","409");
				return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
			}
		}else {
		CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Customer Not Found..!","409");
		return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
	}
	
	}
	
	
}


