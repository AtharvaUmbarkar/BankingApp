package com.bankingapp.models;

import java.util.Date;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;

@Entity
public class Transaction {
	@Id
	@GeneratedValue
	private int txnId;
	@Column(nullable=false)
	@Pattern(regexp="^Withdraw|Deposit|NEFT|RTGS|IMPS$")
	private String txnType; // withdraw, deposit, NEFT, etc
	@CreatedDate
	private Date txnDate;
	@Column(nullable=false)
	@Range(min = 1, message = "Amount should be greater than 0")
	private int txnAmount;
	@Column(nullable=false)
	@Value("${some.key:0}")
	private int senderBalance;
	@Column(nullable=false)
	@Value("${some.key:0}")
	private int receiverBalance;
	@Column(nullable=false)
	@Value("${some.key:Pending}")
	@Pattern(regexp="^Pending|Successful|failed$")
	private String txnStatus;
	private String userRemarks;
	
	@ManyToOne
	@JoinColumn(name="sender_accountNumber", referencedColumnName="accountNumber")
	private Account senderAccount;
	
	@ManyToOne
	@JoinColumn(name="receiver_accountNumber", referencedColumnName="accountNumber")
	private Account receiverAccount;
	
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
	public int getSenderBalance() {
		return senderBalance;
	}
	public void setSenderBalance(int senderBalance) {
		this.senderBalance = senderBalance;
	}
	public int getReceiverBalance() {
		return receiverBalance;
	}
	public void setReceiverBalance(int receiverBalance) {
		this.receiverBalance = receiverBalance;
	}
	public String getTxnStatus() {
		return txnStatus;
	}
	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
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
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}

	
}
