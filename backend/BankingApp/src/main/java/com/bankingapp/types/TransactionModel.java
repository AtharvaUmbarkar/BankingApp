package com.bankingapp.types;

import java.util.List;

import com.bankingapp.models.Transaction;


public class TransactionModel {
	
	private Transaction transaction;
	private List<Long> accountNumbers;
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public List<Long> getAccountNumbers() {
		return accountNumbers;
	}
	public void setAccountNumbers(List<Long> accountNumbers) {
		this.accountNumbers = accountNumbers;
	}
	
}
