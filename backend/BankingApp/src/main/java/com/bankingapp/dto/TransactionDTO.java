package com.bankingapp.dto;

import java.util.Date;
import com.bankingapp.models.Account;

public class TransactionDTO {
	
	private int txnId;
	private String txnType;
	private Date txnDate;
	private double txnAmount;
	private double senderBalance;
	private double receiverBalance;
	private String txnStatus;
	private String userRemarks;
	private Account senderAccount;
	private Account receiverAccount;
	public int getTxnId() {
		return txnId;
	}
	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public Date getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}
	public double getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(double txnAmount) {
		this.txnAmount = txnAmount;
	}
	public double getSenderBalance() {
		return senderBalance;
	}
	public void setSenderBalance(double senderBalance) {
		this.senderBalance = senderBalance;
	}
	public double getReceiverBalance() {
		return receiverBalance;
	}
	public void setReceiverBalance(double receiverBalance) {
		this.receiverBalance = receiverBalance;
	}
	public String getTxnStatus() {
		return txnStatus;
	}
	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}
	public Account getSenderAccount() {
		return senderAccount;
	}
	public void setSenderAccount(Account senderAccount) {
		this.senderAccount = senderAccount;
	}
	public Account getReceiverAccount() {
		return receiverAccount;
	}
	public void setReceiverAccount(Account receiverAccount) {
		this.receiverAccount = receiverAccount;
	}
}
