package com.bankingapp.models;

import java.util.Date;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@JsonIgnoreProperties("Inspection")
public class Transaction {
	@Id
//	@GeneratedValue
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="transaction_generator")
	@SequenceGenerator(
			name="transaction_generator",
			sequenceName = "transaction_seq",
			initialValue=1000000000,
			allocationSize=1)
	private int txnId;
	@Column(nullable=false)
	@NotNull(message = "Transaction type can not be null")
	@Pattern(regexp="^Withdraw|Deposit|NEFT|RTGS|IMPS$")
	private String txnType; // withdraw, deposit, NEFT, etc
	@CreatedDate
	@Column(nullable = false)
	@NotNull(message = "Transaction Date can not be null")
	private Date txnDate = new Date();
	@Column(nullable=false)
	@NotNull(message = "Amount can not be null")
	@Range(min = 1, message = "Amount should be greater than 0")
	private double txnAmount;
	@Column(nullable=false)
	@NotNull(message = "Sender balance can not be null")
	@Value("${some.key:0}")
	private double senderBalance;
	@Column(nullable=false)
	@NotNull(message = "Receiver balance can not be null")
	@Value("${some.key:0}")
	private double receiverBalance;
	@Column(nullable=false)
	@NotNull(message = "Status can not be null")
	@Value("${some.key:Pending}")
	@Pattern(regexp="^Pending|Successful|failed$")
	private String txnStatus;
	private String userRemarks;
	@JsonBackReference(value="acnt-trans1")
	@ManyToOne
	@JoinColumn(name="sender_accountNumber", referencedColumnName="accountNumber")
	private Account senderAccount;
	@JsonBackReference(value="acnt-trans2")
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
