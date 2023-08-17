package com.bankingapp.types;

public class NetBankingModel {
	
	private int accountNumber;
	private String userName;
	private String loginPassword;
	private String confirmLoginPassword;
	private String transactionPassword;
	private String confirmTransactionPassword;
	private int otp;
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getConfirmLoginPassword() {
		return confirmLoginPassword;
	}
	public void setConfirmLoginPassword(String confirmLoginPassword) {
		this.confirmLoginPassword = confirmLoginPassword;
	}
	public String getTransactionPassword() {
		return transactionPassword;
	}
	public void setTransactionPassword(String transactionPassword) {
		this.transactionPassword = transactionPassword;
	}
	public String getConfirmTransactionPassword() {
		return confirmTransactionPassword;
	}
	public void setConfirmTransactionPassword(String confirmTransactionPassword) {
		this.confirmTransactionPassword = confirmTransactionPassword;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	
	
	

}
