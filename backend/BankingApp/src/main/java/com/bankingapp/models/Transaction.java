package com.bankingapp.models;

import java.util.Date;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;

@Entity
public class Transaction {
	@Id
	private int txnId;
	private Date txnDate;
	private String txnType; // (DR/CR)
	@Range(min = 1, message = "Amount should be greater than 0")
	@Pattern(regexp = "^[0-9]+$", message = "Please enter a numerical Value")
	private int txnAmount;
	private String txnDescription;
	private String userRemarks;
	
	@ManyToOne
	@JoinColumn(name="accountNumber")
	private Account account;

	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public int getTxnId() {
		return txnId;
	}
	public void setTxnId(int txnId) {
		this.txnId = txnId;
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
