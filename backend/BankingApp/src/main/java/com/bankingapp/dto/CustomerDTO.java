package com.bankingapp.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.bankingapp.models.Account;
import com.bankingapp.models.Beneficiary;
import com.bankingapp.models.Transaction;

public class CustomerDTO {

	private int customerId;
	private String title;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String fatherName;
	private String emailId;
	private LocalDate dateOfBirth;
	private String mobileNumber;
	private String aadhaarNumber;
	private String tempAddressLine1;
	private String tempAddressLine2;
	private String tempLandmark;
	private String tempState;
	private String tempCity;
	private String tempPincode;
	private String permAddressLine1;
	private String permAddressLine2;
	private String permLandmark;
	private String permState;
	private String permCity;
	private String permPincode;
	
	private String occupation;
	private String sourceOfIncome;
	private int grossAnnualIncome;
	
	private boolean netBankingEnabled;
	private Date lastLogin;
	private String userName;
	private String token;
public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	//	private String loginPassword;
//	private String transactionPassword;
//	private List<Account> accounts;
//	private List<Beneficiary> beneficiaries;
//	private List<Transaction> transactions;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	public String getTempAddressLine1() {
		return tempAddressLine1;
	}
	public void setTempAddressLine1(String tempAddressLine1) {
		this.tempAddressLine1 = tempAddressLine1;
	}
	public String getTempAddressLine2() {
		return tempAddressLine2;
	}
	public void setTempAddressLine2(String tempAddressLine2) {
		this.tempAddressLine2 = tempAddressLine2;
	}
	public String getTempLandmark() {
		return tempLandmark;
	}
	public void setTempLandmark(String tempLandmark) {
		this.tempLandmark = tempLandmark;
	}
	public String getTempState() {
		return tempState;
	}
	public void setTempState(String tempState) {
		this.tempState = tempState;
	}
	public String getTempCity() {
		return tempCity;
	}
	public void setTempCity(String tempCity) {
		this.tempCity = tempCity;
	}
	public String getTempPincode() {
		return tempPincode;
	}
	public void setTempPincode(String tempPincode) {
		this.tempPincode = tempPincode;
	}
	public String getPermAddressLine1() {
		return permAddressLine1;
	}
	public void setPermAddressLine1(String permAddressLine1) {
		this.permAddressLine1 = permAddressLine1;
	}
	public String getPermAddressLine2() {
		return permAddressLine2;
	}
	public void setPermAddressLine2(String permAddressLine2) {
		this.permAddressLine2 = permAddressLine2;
	}
	public String getPermLandmark() {
		return permLandmark;
	}
	public void setPermLandmark(String permLandmark) {
		this.permLandmark = permLandmark;
	}
	public String getPermState() {
		return permState;
	}
	public void setPermState(String permState) {
		this.permState = permState;
	}
	public String getPermCity() {
		return permCity;
	}
	public void setPermCity(String permCity) {
		this.permCity = permCity;
	}
	public String getPermPincode() {
		return permPincode;
	}
	public void setPermPincode(String permPincode) {
		this.permPincode = permPincode;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getSourceOfIncome() {
		return sourceOfIncome;
	}
	public void setSourceOfIncome(String sourceOfIncome) {
		this.sourceOfIncome = sourceOfIncome;
	}
	public int getGrossAnnualIncome() {
		return grossAnnualIncome;
	}
	public void setGrossAnnualIncome(int grossAnnualIncome) {
		this.grossAnnualIncome = grossAnnualIncome;
	}
	public boolean isNetBankingEnabled() {
		return netBankingEnabled;
	}
	public void setNetBankingEnabled(boolean netBankingEnabled) {
		this.netBankingEnabled = netBankingEnabled;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
