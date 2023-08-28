package com.bankingapp.models;


import java.util.Collections;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.bankingapp.types.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Admin {
	
	@Id
	@Column(name="user_name", unique=true)
	@Pattern(regexp="^[A-Za-z0-9]{8,}$", message="must contain only digits and alphabets and should be of length 8")
	private String userName;
	
	@Column(name="login_password", nullable = false)
//	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
	private String loginPassword;	
	
	@Column(nullable=false)
	@NotBlank(message="cannot be blank")
	private String title;
	
	@Column(name="name", nullable=false)
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String name;
	
	@Column(name="mobile", nullable=false, unique=true)
	@Pattern(regexp ="^\\d{10}$")
	private String mobileNumber;
	
	@Column(name="email_id", unique=true)
	private String emailId;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	
}
