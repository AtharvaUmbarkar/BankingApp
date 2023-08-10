package com.bankingapp.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Beneficiary {
	@Id
	@GeneratedValue
	private int beneficiaryID;
	
	@Column(nullable=false)
	@Range(min=1)
	private int customerID;
	
	@Column(nullable=false)
	@Range(min=1)
	private int accountNumber;
	
	@Column(nullable=false)
	@NotBlank(message="Beneficiary name cannot be null")
	@Length(min=3, max=30, message="name must be between 3-30 characters")
	private String name;
	
	@Length(max=30, message="nickname cannot have more than 30 characters")
	private String nickName;
	public int getBeneficiaryID() {
		return beneficiaryID;
	}
	public void setBeneficiaryID(int beneficiaryID) {
		this.beneficiaryID = beneficiaryID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
