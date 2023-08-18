package com.bankingapp.models;

import java.util.Date;
//import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Pattern;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="account_generator")
	@SequenceGenerator(
			name="account_generator",
			sequenceName = "account_seq",
			initialValue=1000000000,
			allocationSize=1)
	private long accountNumber;
	@Length(max = 10)
	@Pattern(regexp="^[A-Za-z]+$", message="Account Type can only contain Characters") 
	@Value("${some.key:Savings}")
	private String accountType;
	@Value("${some.key:1000}")
	private double accountBalance;
//	@Value("${some.key:2023-08-14T01:30:00.000-05:00}")
	@CreatedDate
	private Date accountCreationDate =  new Date();
	private boolean netBankingOpted; //need to be removed
	private boolean debitCardAvailed;
	
	public boolean isDebitCardAvailed() {
		return debitCardAvailed;
	}
	public void setDebitCardAvailed(boolean debitCardAvailed) {
		this.debitCardAvailed = debitCardAvailed;
	}
	
//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	
//	@JsonManagedReference(value="acnt-txns1")
	@OneToMany(mappedBy="senderAccount", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Transaction> debitTransactions;
	
//	@JsonManagedReference(value="acnt-txns2")
	@OneToMany(mappedBy="receiverAccount", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Transaction> creditTransactions;
	
//	@OneToMany(mappedBy="account", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
//	private List<Beneficiary> beneficiaries;
//	
//
//	public List<Beneficiary> getBeneficiaries() {
//		return beneficiaries;
//	}
//	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
//		this.beneficiaries = beneficiaries;
//	}
	public List<Transaction> getDebitTransactions() {
		return debitTransactions;
	}
	public void setDebitTransactions(List<Transaction> debitTransactions) {
		this.debitTransactions = debitTransactions;
	}
	public List<Transaction> getCreditTransactions() {
		return creditTransactions;
	}
	public void setCreditTransactions(List<Transaction> creditTransactions) {
		this.creditTransactions = creditTransactions;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Date getAccountCreationDate() {
		return accountCreationDate;
	}
	public void setAccountCreationDate(Date accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}
	public boolean isNetBankingOpted() {
		return netBankingOpted;
	}
	public void setNetBankingOpted(boolean netBankingOpted) {
		this.netBankingOpted = netBankingOpted;
	}
	
	
}

