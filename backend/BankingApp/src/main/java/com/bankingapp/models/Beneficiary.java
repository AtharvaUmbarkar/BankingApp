package com.bankingapp.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Beneficiary {
	@Id
	@GeneratedValue
	private int beneficiaryID;
	
	@Column(nullable=false)
	@NotBlank(message="Beneficiary name cannot be blank")
	@Length(min=3, max=30, message="Name size must be between 3-30 characters")
//	@Pattern(regexp="^[A-za-z]+$")
	private String beneficiaryName;
	
	@Length(max=30, message="nickname cannot have more than 30 characters")
	private String beneficiaryNickname;

//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;

//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="accountNumber")
	private Account account;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public int getBeneficiaryID() {
		return beneficiaryID;
	}

	public void setBeneficiaryID(int beneficiaryID) {
		this.beneficiaryID = beneficiaryID;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBeneficiaryNickname() {
		return beneficiaryNickname;
	}

	public void setBeneficiaryNickname(String beneficiaryNickname) {
		this.beneficiaryNickname = beneficiaryNickname;
	}

	
}
