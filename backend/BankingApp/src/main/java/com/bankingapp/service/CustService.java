package com.bankingapp.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.InvalidTypeException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.types.ForgotPasswordModel;
import com.bankingapp.types.ChangePasswordModel;
import com.bankingapp.types.ChangeUserNameModel;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;

import jakarta.transaction.Transactional;

@Service
public class CustService {
	@Autowired
	CustomerRepo custRepo;
	@Autowired
	AccountRepo accRepo;
	public Customer saveCustomer(Customer cust)
	{
		Customer obj=custRepo.save(cust);
		return obj;
	}
	
	@Transactional
	public Customer validateCustomer(LoginModel loginUser) throws UnauthorizedAccessException, ResourceNotFoundException
	{
		Customer cust = null;
		String userName = loginUser.getUsername();
		Optional<Customer> objt = custRepo.findByUserName(userName);
		if (objt.isPresent())
		{
			cust = objt.get();
			if(!cust.isUnLocked()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(cust.getLastLogin());
				cal.add(Calendar.DATE, 1);
				Date current = new Date();
				if(cal.getTime().after(current)) {
					throw new UnauthorizedAccessException("Exceeded 3 login attemps, please change your password to login");
				}
			}
			if (loginUser.getPassword().equals(cust.getLoginPassword())) {
				custRepo.changeLastLogin(new Date(),0,true,userName); //also set 0 failed attempts
				return cust;
			}
			else {
				int noAttempts = cust.getNoFailedAttemps()+1;
				if(noAttempts>2) {
					//update time, attempts, unLocked
					custRepo.changeLastLogin(new Date(),0,false,userName);
					throw new UnauthorizedAccessException("Invalid Credentials, your account have be locked for 1 day");
				}
				else {
					//update time and attempts, unlocked
					custRepo.changeLastLogin(new Date(),noAttempts,true,userName);
					throw new UnauthorizedAccessException(String.format("Invalid Credentials, %d more attempts remaining", 3-noAttempts));
 				}
			}
		}
		throw new ResourceNotFoundException("Customer not Present");
	}
	
	public List<Account> fetchAccounts(String username) throws ResourceNotFoundException
	{
		Optional<Customer> obj = custRepo.findByUserName(username);
		if (!obj.isPresent()) {
			throw new ResourceNotFoundException("Customer not found");
		}
		Customer cust = obj.get();
		List<Account> accounts = cust.getAccounts();
		return accounts;
	}
	
	@Transactional
	public String netbankingreg(NetBankingModel nb) throws AlreadyExistsException, ResourceNotFoundException
	{
		String result = "";
		if(nb.getLoginPassword().equals(nb.getTransactionPassword())) {
			return result = "Login and Transaction password cannot be same";
		}
		Optional<Account> obj = accRepo.findById(nb.getAccountNumber());
		if (obj.isPresent())
		{
			Customer cust = obj.get().getCustomer();

			if (cust.isNetBankingEnabled())
			{
//				result = "User already exists";
				throw new AlreadyExistsException("User Already Exists");
			}
			else
			{
				custRepo.setUserName(nb.getUserName(), nb.getLoginPassword(), nb.getTransactionPassword(), cust.getCustomerId());
				result = "successfully registered for net banking";
			}
		}
		else
		{
//			result = "Account does not exist, Open an account first";
			throw new ResourceNotFoundException("Account does not exist, Open an account first");
		}
		return result;
	}
	
	@Transactional
	public String forgotPassword(ChangePasswordModel obj ) throws ResourceNotFoundException, InvalidTypeException 
	{
		String userName = obj.getUserName();
		Optional<Customer> optCust= custRepo.findByUserName(userName);
		if(optCust.isPresent()) {
			if(obj.getPasswordType().equals("Login")) {
				custRepo.changeLoginPassword(obj.getNewPassword(), userName);
			}
			else if(obj.getPasswordType().equals("Transactional")){
				custRepo.changeTransactionPassword(obj.getNewPassword(), userName);
			}
			else {
//				return result = "Not a valid password type";
				throw new InvalidTypeException("Invalid Password type");
			}
//			if(rowsAffected > 0) {
//				result = "Successfully changed the Password";
//			}
//			else {
//				result = "Failed to change the password";
//			}
		}
		else {
			throw new ResourceNotFoundException("Customer does not exist");
//			result = "Customer does not exist";
		}
		return "Success";
	}
	
	@Transactional
	public String changePassword(ChangePasswordModel obj) throws ResourceNotFoundException, InvalidTypeException 
	{
		String userName = obj.getUserName();
		Optional<Customer> optCust= custRepo.findByUserName(userName);
		if(optCust.isPresent()) {
			Customer cust = optCust.get();
			String curPassword = obj.getCurrentPassword();
			if(obj.getPasswordType().equals("Login") && cust.getLoginPassword().equals(curPassword)) {
				custRepo.changeLoginPassword(obj.getNewPassword(), userName);
			}
			else if(obj.getPasswordType().equals("Transactional") && cust.getTransactionPassword().equals(curPassword)){
				custRepo.changeTransactionPassword(obj.getNewPassword(), userName);
			}
			else {
//				return result = "Not a valid password type";
				throw new InvalidTypeException("Invalid Passwrd Type or passwords doesn't match");
			}
//			if(rowsAffected > 0) {
//				result = "Successfully changed the Password";
//			}
//			else {
//				result = "Failed to change the password";
//			}
		}
		else {
			throw new ResourceNotFoundException("Customer does not exist");
//			result = "Customer does not exist";
		}
		return "Success";
	}
	
	@Transactional
	public String changeUserName(ChangeUserNameModel obj) throws ResourceNotFoundException, InvalidTypeException {
		Optional<Customer> optCust = custRepo.findByAadhaarNumber(obj.getAadhaarNumber());
		if(optCust.isPresent()) {
			Optional<Customer> checkCust = custRepo.findByUserName(obj.getUserName());
			if(checkCust.isPresent())
//				return "username is already taken";
				throw new InvalidTypeException("Username is already Taken");
			custRepo.changeUserName(obj.getUserName(), obj.getAadhaarNumber());
			return "successful";
		}
		else {
			throw new ResourceNotFoundException("Customer does not exist");
		}
//		return "User with given aadhaaar number doesn't exist";
	}
	
	public Customer fetchUser(int custId) throws ResourceNotFoundException {
		Customer cust = null;
		Optional<Customer> obj = custRepo.findById(custId);
		if(obj.isPresent()) {
			cust = obj.get();
			return cust;
			}
		throw new ResourceNotFoundException("Customer Does Not Exist");
		
	}
	
	@Transactional
	public boolean toggleUser(String userName) throws ResourceNotFoundException {
		Optional<Customer> obj = custRepo.findByUserName(userName);
		if(obj.isPresent()) {
			Customer cust = obj.get(); 
			int rowsAffected = custRepo.toggleUser(!cust.isUnLocked(), userName);
			if(rowsAffected > 0)
				return true;
			else
				return false;
		}
		else {
			throw new ResourceNotFoundException("Customer not found");
		}
	}

	public List<Object> getCustomerAndAccountDetails(int custId) {
		return custRepo.getCustomerAndAccountDetails(custId);
	}

}
