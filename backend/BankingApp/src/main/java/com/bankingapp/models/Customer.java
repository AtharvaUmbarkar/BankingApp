package com.bankingapp.models;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_generator")
	@SequenceGenerator(
			name="customer_generator",
			sequenceName = "customer_seq",
			initialValue=10000000,
			allocationSize=1)
	private int customerId;
	
	@Column(nullable=false)
	@NotNull(message = "Title can not be null")
	@NotBlank(message="cannot be blank")
	private String title;
	@Column(name="first_name", nullable=false)
	@NotNull(message = "First name can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
//	@Pattern(regexp="^[A-Z][a-z]+$")
	private String firstName;
	@Column(name="middle_name")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String middleName;
	@Column(name="last_name", nullable=false)
	@NotNull(message = "Last name can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String lastName;
	@Column(name="father_name", nullable=false)
	@NotNull(message = "Father name can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String fatherName;
	@Column(name="email_id", unique=true, nullable=false)
	@NotNull(message = "EmailID can not be null")
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String emailId;
	@Column(name="dob", nullable=false)
	@NotNull(message = "DOB can not be null")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	@Column(name="mobile", nullable=false, unique=true)
	@NotNull(message = "Mobile number can not be null")
	@Pattern(regexp ="^\\d{10}$")
//	@Length(min=10, max=10, message="mobile number must be of 10 digits")
	private String mobileNumber;
	@Column(name="aadhaar", nullable=false, unique=true)
	@NotNull(message = "Aadhaar number can not be null")
	@Pattern(regexp="^[0-9]{12}$")
	private String aadhaarNumber;
	
	@Column(name="temp_line1", nullable=false)
	@NotNull(message = "Temporary Line 1 can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String tempAddressLine1;
	@Column(name="temp_line2", nullable=false)
	@NotNull(message = "Temporary Line 2 can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String tempAddressLine2;
	@Column(name="temp_landmark", nullable=false)
	@NotNull(message = "Temporary Landmark can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String tempLandmark;
	@Column(name="temp_state", nullable=false)
	@NotNull(message = "Temporary State can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String tempState;
	@Column(name="temp_city", nullable=false)
	@NotNull(message = "Temporary City can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String tempCity;
	@Column(name="temp_pincode", nullable=false)
	@NotNull(message = "Temporary Pincode can not be null")
	@Pattern(regexp="^[0-9]{6}$", message="must be of 6 digits")
	private String tempPincode;
	
	@Column(name="perm_line1", nullable=false)
	@NotNull(message = "Permanent Line1 can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String permAddressLine1;
	@Column(name="perm_line2", nullable=false)
	@NotNull(message = "Permanent Line2 can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String permAddressLine2;
	@Column(name="perm_landmark", nullable=false)
	@NotNull(message = "Permanent Landmark can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String permLandmark;
	@Column(name="perm_state", nullable=false)
	@NotNull(message = "Permanent State can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String permState;
	@Column(name="perm_city", nullable=false)
	@NotNull(message = "Permanent City can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String permCity;
	@Column(name="perm_pincode", nullable=false)
	@NotNull(message = "Permanent Pincode can not be null")
	@Pattern(regexp="^[0-9]{6}$", message="must be of 6 digits")
	private String permPincode;
	
	@Column(nullable=false)
	@NotNull(message = "Occupation can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String occupation;
	@Column(name="source_of_income", nullable=false)
	@NotNull(message = "Source of Income can not be null")
	@Length(min=3, max=30, message="must be between 3-30 characters")
	private String sourceOfIncome;
	@Column(name="gross_annual_income", nullable=false)
	@NotNull(message = "Annual Income can not be null")
	@Range(min=0, message="must be non negative")
	private int grossAnnualIncome;
	
	@Column(name="net_banking", nullable=false)
	@NotNull(message = "netBankingEnabled can not be null")
	private boolean netBankingEnabled=false;
	@Column(name="last_login") //last login attempt
	private Date lastLogin;
	@Column(name="user_name", unique=true)
	@Pattern(regexp="^[A-Za-z0-9]{8,}$", message="must contain only digits and alphabets and should be of length 8")
	private String userName;
	@Column(name="login_password")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
	private String loginPassword;
	@Column(name="transaction_password")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
	private String transactionPassword;
	@Value("${some.key:0}")
	private int noFailedAttemps;
	private boolean unLocked=true;
	@Column(nullable = false)
	@NotNull(message = "Enabledcan not be null")
	private boolean enabled=true;

	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Account> accounts;
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Beneficiary> beneficiaries;
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private List<Transaction> transactions;
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public int getNoFailedAttemps() {
		return noFailedAttemps;
	}
	public void setNoFailedAttemps(int noFailedAttemps) {
		this.noFailedAttemps = noFailedAttemps;
	}
	public boolean isUnLocked() {
		return unLocked;
	}
	public void setUnLocked(boolean unLocked) {
		this.unLocked = unLocked;
	}
	public List<Account> getAccounts() {
		return this.accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}
	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
//	@JsonIgnore
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
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
	public void setDateOfBirth(LocalDate customerDob) {
		this.dateOfBirth = customerDob;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String customerPhone) {
		this.mobileNumber = customerPhone;
	}
	public String getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(String customerAadhaar) {
		this.aadhaarNumber = customerAadhaar;
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
	@JsonIgnore
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	@JsonIgnore
	public String getTransactionPassword() {
		return transactionPassword;
	}
	public void setTransactionPassword(String transactionPassword) {
		this.transactionPassword = transactionPassword;
	}
	
	
	
	
	
}
