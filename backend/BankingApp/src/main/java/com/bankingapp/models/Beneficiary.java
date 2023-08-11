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
	@Column(nullable=false)
	@Range(min=1)
	private int beneficiaryAccountNumber;
	
	@Column(nullable=false)
	@NotBlank(message="Beneficiary name cannot be blank")
	@Length(min=3, max=30, message="Name size must be between 3-30 characters")
	private String beneficiaryName;
	
	@Length(max=30, message="nickname cannot have more than 30 characters")
	private String beneficiaryNickname;

	public int getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(int beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
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
