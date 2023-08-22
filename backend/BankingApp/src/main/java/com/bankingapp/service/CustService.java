package com.bankingapp.service;

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
		String result = "";
		Customer cust = null;
		Optional<Customer> objt = custRepo.findByUserName(loginUser.getUsername());
		if (objt.isPresent())
		{
			cust = objt.get();
			if (loginUser.getPassword().equals(cust.getLoginPassword())) {
				custRepo.changeLastLogin(new Date(),loginUser.getUsername());
				return cust;
			}
			else {
				throw new UnauthorizedAccessException("Invalid Credentials");
			}
		}
		throw new ResourceNotFoundException("Customer not Present");
		
//		if (cust==null)
//		{
//			result = "Invalid user"; 
//		}
//		else
//		{
//			if (loginUser.getPassword().equals(cust.getLoginPassword()))
//			{
//				result = "Login Success";
//			}
//			else
//			{
//				result = "Login Failed";
//			}
//		}
//		return cust;
	}
	
	public List<Integer> fetchAccounts(String username) throws ResourceNotFoundException, NoDataFoundException
	{
		Optional<Customer> obj = custRepo.findByUserName(username);
		if (!obj.isPresent()) {
			throw new ResourceNotFoundException("Customer not found");
		}
		int custId = (obj.get()).getCustomerId();
		List<Integer> accounts = accRepo.findByAccountNumber(custId);
		if (accounts.isEmpty()) {
			throw new NoDataFoundException("");
		}
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
	public String changePassword(ChangePasswordModel obj, String userName) throws ResourceNotFoundException, InvalidTypeException 
	{
		String result="";
		Optional<Customer> optCust= custRepo.findByUserName(userName);
		if(optCust.isPresent()) {
			int rowsAffected;
			if(obj.getPasswordType().equals("Login")) {
				rowsAffected = custRepo.changeLoginPassword(obj.getPassword(), userName);
			}
			else if(obj.getPasswordType().equals("Transactional")){
				rowsAffected = custRepo.changeTransactionPassword(obj.getPassword(), userName);
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
		return result;
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
}
