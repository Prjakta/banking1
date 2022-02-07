package com.banking.auth.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserTransactionsDetails {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String accountNumber;
	private String userAccountNumber;
	private long transferAmount;
	private String createdAt;
	private String updatedAt;
	private String status;
	public UserTransactionsDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserTransactionsDetails(long id, String accountNumber, String userAccountNumber, long transferAmount,
			String createdAt, String updatedAt, String status) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.userAccountNumber = userAccountNumber;
		this.transferAmount = transferAmount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getUserAccountNumber() {
		return userAccountNumber;
	}
	public void setUserAccountNumber(String userAccountNumber) {
		this.userAccountNumber = userAccountNumber;
	}
	public long getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(long transferAmount) {
		this.transferAmount = transferAmount;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UserTransactionsDetails [id=" + id + ", accountNumber=" + accountNumber + ", userAccountNumber="
				+ userAccountNumber + ", transferAmount=" + transferAmount + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", status=" + status + "]";
	}
	
}