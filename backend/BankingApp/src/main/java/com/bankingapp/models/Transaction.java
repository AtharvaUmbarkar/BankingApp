package com.bankingapp.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transaction {
	@Id
	private int txnId;
	private int accountNumber;
	private Date txnDate;
	private String txnType; // (DR/CR)
	private int txnAmount;
	private String txnDescription;
	private String userRemarks;
	public int getTxnId() {
		return txnId;
	}
	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Date getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public int getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(int txnAmount) {
		this.txnAmount = txnAmount;
	}
	public String getTxnDescription() {
		return txnDescription;
	}
	public void setTxnDescription(String txnDescription) {
		this.txnDescription = txnDescription;
	}
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}

	
}
