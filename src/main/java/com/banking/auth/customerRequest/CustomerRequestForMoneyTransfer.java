package com.banking.auth.customerRequest;

import org.springframework.stereotype.Service;

@Service
public class CustomerRequestForMoneyTransfer {
	
		private String accountNumber;
		private String userAccountNumber;
		private long transferAmount;
		private String branchName;
		private String ifsc;
		private String transactionPin;
		public CustomerRequestForMoneyTransfer() {
			super();
			// TODO Auto-generated constructor stub
		}
		public CustomerRequestForMoneyTransfer(String accountNumber, String userAccountNumber, long transferAmount,
				String branchName, String ifsc, String transactionPin) {
			super();
			this.accountNumber = accountNumber;
			this.userAccountNumber = userAccountNumber;
			this.transferAmount = transferAmount;
			this.branchName = branchName;
			this.ifsc = ifsc;
			this.transactionPin = transactionPin;
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
		public String getBranchName() {
			return branchName;
		}
		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}
		public String getIfsc() {
			return ifsc;
		}
		public void setIfsc(String ifsc) {
			this.ifsc = ifsc;
		}
		public String getTransactionPin() {
			return transactionPin;
		}
		public void setTransactionPin(String transactionPin) {
			this.transactionPin = transactionPin;
		}
		@Override
		public String toString() {
			return "CustomerRequestForMoneyTransfer [accountNumber=" + accountNumber + ", userAccountNumber="
					+ userAccountNumber + ", transferAmount=" + transferAmount + ", branchName=" + branchName
					+ ", ifsc=" + ifsc + ", transactionPin=" + transactionPin + "]";
		}
		
		
		
}
