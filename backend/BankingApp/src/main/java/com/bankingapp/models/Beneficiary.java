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
	private int id;
	
	@Column(nullable=false)
	@NotBlank(message="Beneficiary name cannot be blank")
	@Length(min=3, max=30, message="Name size must be between 3-30 characters")
//	@Pattern(regexp="^[A-za-z]+$")
	private String name;
	
	@Length(max=30, message="nickname cannot have more than 30 characters")
	private String nickname;

//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;

//	@JsonBackReference
//	@ManyToOne
//	@JoinColumn(name="accountNumber")
	private long accountNumber;

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public Account getAccount() {
//		return account;
//	}
//
//	public void setAccount(Account account) {
//		this.account = account;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
}
