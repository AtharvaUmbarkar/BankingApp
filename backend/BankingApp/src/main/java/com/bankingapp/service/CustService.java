package com.bankingapp.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.InvalidTypeException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.interfaces.CustomerServiceInterface;
import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.types.ChangePasswordModel;
import com.bankingapp.types.ChangeUserNameModel;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;
import com.bankingapp.types.UserRole;

import jakarta.transaction.Transactional;

@Service
public class CustService implements UserDetailsService, CustomerServiceInterface {
	@Autowired
	CustomerRepo custRepo;
	@Autowired
	AccountRepo accRepo;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Customer cust = custRepo.findByUserName(userName).get();
		if(cust!=null) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority(UserRole.ROLE_USER.toString()));
			return new org.springframework.security.core.userdetails.User(cust.getUserName(), cust.getLoginPassword(), cust.isEnabled(), true, true, true,authorities );
		}
		else {
			throw new UsernameNotFoundException("User name not found");
		}
	}
		
	@Transactional
	public void increaseAttempts(String userName) throws UnauthorizedAccessException {
		int noAttempts = custRepo.findByUserName(userName).get().getNoFailedAttemps()+1;
		if(noAttempts>2) {
			custRepo.changeLastLogin(new Date(),0,false,userName);
			throw new UnauthorizedAccessException("Invalid Credentials, your account have be locked for 1 day");
		}
		else {
			custRepo.changeLastLogin(new Date(),noAttempts,true,userName);
			throw new UnauthorizedAccessException(String.format("Invalid Credentials, %d more attempts remaining", 3-noAttempts));
			}
	}
	
	@Transactional
	public void changeLastLogin(String userName) throws UnauthorizedAccessException {
		custRepo.changeLastLogin(new Date(),0,true,userName);
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
	public String netBankingReg(NetBankingModel nb) throws AlreadyExistsException, ResourceNotFoundException
	{
		if(nb.getLoginPassword().equals(nb.getTransactionPassword())) {
			return "Login and Transaction password cannot be same";
		}
		Optional<Account> obj = accRepo.findById(nb.getAccountNumber());
		if (obj.isPresent())
		{
			Customer cust = obj.get().getCustomer();
			if(!obj.get().isActive()) {
				throw new ResourceNotFoundException("Account is not activated");
			}
			if (cust.isNetBankingEnabled())
			{
				throw new AlreadyExistsException("User Already Exists");
			}
			else
			{
				custRepo.setUserName(nb.getUserName(), bcryptEncoder.encode(nb.getLoginPassword()), bcryptEncoder.encode(nb.getTransactionPassword()), cust.getCustomerId());
				return "successfully registered for net banking";
			}
		}
		else
		{
			throw new ResourceNotFoundException("Account does not exist, Open an account first");
		}
	}
	
	@Transactional
	public String forgotPassword(ChangePasswordModel obj ) throws ResourceNotFoundException, InvalidTypeException 
	{
		String userName = obj.getUserName();
		Optional<Customer> optCust= custRepo.findByUserName(userName);
		if(optCust.isPresent()) {
			if(obj.getPasswordType().equals("Login")) {
				custRepo.changeLoginPassword(bcryptEncoder.encode(obj.getNewPassword()), userName);
			}
			else if(obj.getPasswordType().equals("Transactional")){
				custRepo.changeTransactionPassword(bcryptEncoder.encode(obj.getNewPassword()), userName);
			}
			else {
				throw new InvalidTypeException("Invalid Password type");
			}
		}
		else {
			throw new ResourceNotFoundException("Customer does not exist");
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
			String curPassword = bcryptEncoder.encode(obj.getCurrentPassword());
			if(obj.getPasswordType().equals("Login") && cust.getLoginPassword().equals(curPassword)) {
				custRepo.changeLoginPassword(bcryptEncoder.encode(obj.getNewPassword()), userName);
			}
			else if(obj.getPasswordType().equals("Transactional") && cust.getTransactionPassword().equals(curPassword)){
				custRepo.changeTransactionPassword(obj.getNewPassword(), userName);
			}
			else {
				throw new InvalidTypeException("Invalid Passwrd Type or passwords doesn't match");
			}
		}
		else {
			throw new ResourceNotFoundException("Customer does not exist");
		}
		return "Success";
	}
	
	@Transactional
	public String changeUserName(ChangeUserNameModel obj) throws ResourceNotFoundException, UnauthorizedAccessException {
		Optional<Customer> optCust = custRepo.findByAadhaarNumber(obj.getAadhaarNumber());
		if(optCust.isPresent()) {
			Optional<Customer> checkCust = custRepo.findByUserName(obj.getUserName());
			if(checkCust.isPresent())
				throw new UnauthorizedAccessException("Username is already Taken");
			custRepo.changeUserName(obj.getUserName(), obj.getAadhaarNumber());
			return "successful";
		}
		else {
			throw new ResourceNotFoundException("Customer does not exist");
		}
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
	public boolean toggleUser(int custId) throws ResourceNotFoundException {
		Optional<Customer> obj = custRepo.findById(custId);
		if(obj.isPresent()) {
			Customer cust = obj.get(); 
			int rowsAffected = custRepo.toggleUser(!cust.isEnabled(), custId);
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

	public Optional<Customer> findByUserName(String username) {
		
		return custRepo.findByUserName(username);
	}



}
