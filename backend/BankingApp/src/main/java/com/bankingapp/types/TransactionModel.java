package com.bankingapp.types;

import java.util.ArrayList;
import java.util.List;

import com.bankingapp.models.Transaction;


public class TransactionModel {
	
	private Transaction transaction;
	private long senderAccountNumber;
	private long receiverAccountNumber;
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
	
}
