package com.bankingapp.types;

import java.util.Date;


public class TransactionModel {
	private String txnType; // withdraw, deposit, NEFT, etc
	private int txnAmount;
	private String userRemarks;
	private long senderAccount;
	private long receiverAccount;
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
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}
	public long getSenderAccount() {
		return senderAccount;
	}
	public void setSenderAccount(long senderAccount) {
		this.senderAccount = senderAccount;
	}
	public long getReceiverAccount() {
		return receiverAccount;
	}
	public void setReceiverAccount(long receiverAccount) {
		this.receiverAccount = receiverAccount;
	}
	
	
}
