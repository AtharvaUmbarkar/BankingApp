package com.bankingapp.types;

import java.util.ArrayList;
import java.util.List;

import com.bankingapp.models.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("Inspection")
public class TransactionModel {
	
	private Transaction transaction;
	private long senderAccountNumber;
	private long receiverAccountNumber;
	private String transactionPassword;
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public long getSenderAccountNumber() {
		return senderAccountNumber;
	}
	public void setSenderAccountNumber(long senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}
	public long getReceiverAccountNumber() {
		return receiverAccountNumber;
	}
	public void setReceiverAccountNumber(long receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}
	public String getTransactionPassword() {
		return transactionPassword;
	}
	public void setTransactionPassword(String transactionPassword) {
		this.transactionPassword = transactionPassword;
	}
	
}
