package com.banking.auth.CustomerResponseForRegistration;

import java.util.Date;

import com.banking.auth.entities.AccountDetails;
//import com.banking.auth.entities.Customers;

public class CustomerResponseForAccountDetails {
	private Date timestamp;
	private String message;
	private String status;
	private AccountDetails accountDetails;
	public CustomerResponseForAccountDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerResponseForAccountDetails(Date timestamp, String message, String status,
			AccountDetails accountDetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.accountDetails = accountDetails;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AccountDetails getAccountDetails() {
		return accountDetails;
	}
	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}
	@Override
	public String toString() {
		return "CustomerResponseForAccountDetails [timestamp=" + timestamp + ", message=" + message + ", status="
				+ status + ", accountDetails=" + accountDetails + "]";
	}
	

}
