package com.banking.auth.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.auth.CustomerResponseForRegistration.CustomerResponseForAccountDetails;
import com.banking.auth.CustomerResponseForRegistration.CustomerResponseForNoUser;
import com.banking.auth.CustomerResponseForRegistration.CustomerResponseForRegister;
import com.banking.auth.entities.AccountDetails;
import com.banking.auth.entities.Customers;
import com.banking.auth.service.AccountService;
import com.banking.auth.service.CustomerService;
import com.banking.auth.util.Validations;
@CrossOrigin
@RestController
@RequestMapping("/customer/auth")
public class CustomerAuthenticationController {
	
	@Autowired
	CustomerService customerService;
	@Autowired
	Validations validations;
	@Autowired
	AccountService accountService;
	
	@PostMapping("/registerCustomer")
	public ResponseEntity<Object> registerCustomer(@RequestBody Customers customers){
		
		validations.registerCustomer(customers);
		Customers findCustomer = customerService.findCustomerByEmail(customers.getEmail());
		if(findCustomer == null) {

			Customers registerCustomer = customerService.registerCustomer(customers);
			AccountDetails accountDetails = new AccountDetails();
			accountDetails.setAccountNumber(registerCustomer.getAccountNumber());
			accountDetails.setBranchName("Pune");
			accountDetails.setAccountBalance(0);
			accountDetails.setStatus("1");
			accountDetails.setCreatedAt(registerCustomer.getCreatedAt());
			accountDetails.setUpdatedAt(registerCustomer.getUpdatedAt());
			accountDetails.setIfsc("PARK202134");
			accountDetails.setCustomerId(registerCustomer);
			
			customerService.addAccountDetails(accountDetails);
			
			CustomerResponseForRegister responseStructure = new CustomerResponseForRegister(new Date(),"Customer Registration Successful..!","200",registerCustomer);
			return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
			
		}else {
			CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Customer Already Registered with the same email..!","409");
			return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);

			
		}
		
		
	}
	@PostMapping("/loginCustomer")
	public ResponseEntity<Object> loginCustomer(@RequestBody Customers customers){
		validations.loginCustomer(customers);
		Customers findCustomer = customerService.findCustomerByEmail(customers.getEmail());
		if(findCustomer!= null) {
			if(findCustomer.getPassword().equals(customers.getPassword())) {
				CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Login Successful..!","200");
				return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);	
			}else {
				CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Invalid Credentials..!","400");
				return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
			}
			
			
			
		}else {
			CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Customer Not Found..!","409");
			return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
		}

		

	}
	@PostMapping("/fetchAccountDetails")
	public ResponseEntity<Object> fetchAccountDetails(@RequestBody AccountDetails accountDetails){
		
		AccountDetails accountDetail = accountService.fetchAccountDetails(accountDetails.getAccountNumber());
		if(accountDetail!= null) {
			CustomerResponseForAccountDetails responseStructure = new CustomerResponseForAccountDetails(new Date(),"Customer Found!","200", accountDetail);
			return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
			
		}else {

			CustomerResponseForNoUser responseStructure = new CustomerResponseForNoUser(new Date(),"Customer Not Found..!","400");
			return new ResponseEntity<Object> (responseStructure,HttpStatus.OK);
		
			
		}
		
		
	}
	}

	


