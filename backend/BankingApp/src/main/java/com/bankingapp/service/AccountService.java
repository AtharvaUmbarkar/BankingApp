package com.bankingapp.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.interfaces.AccountServiceInterface;
import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.types.CustomerAndAccountModel;
import com.bankingapp.types.UserRole;

import jakarta.transaction.Transactional;

@Service
public class AccountService implements AccountServiceInterface {
	@Autowired 
	private CustomerRepo custRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	public String createAccount(Account account, String userName) throws ResourceNotFoundException
	{
		String result="";
		Customer customer;
		Optional<Customer> obj = custRepo.findByUserName(userName);
		if(!obj.isPresent()) {
//			result = "Customer not found";
			throw new ResourceNotFoundException("Customer not found");
		}
		else {
			customer = obj.get();
			account.setCustomer(customer);
			Account acnt = accountRepo.save(account);
			result = "Account successfully created wth Account No: " + acnt.getAccountNumber();
		}
		return result;
	}
	
	public String createAccountUsingId(Account account, int Id) throws ResourceNotFoundException
	{
		String result="";
		Customer customer;
		Optional<Customer> obj = custRepo.findById(Id);
		if(!obj.isPresent()) {
			throw new ResourceNotFoundException("Customer not found");
		}
		else {
			customer = obj.get();
			account.setCustomer(customer);
			Account acnt = accountRepo.save(account);
			result = "Account successfully created wth Account No: " + acnt.getAccountNumber();
			
		}
		return result;
	}
	
	@Transactional
	public String firstAccount(CustomerAndAccountModel obj) throws AlreadyExistsException
	{
		String result="";
		Customer customer = obj.getCustomer();
		Optional<Customer> optObj = custRepo.findByAadhaarNumber(customer.getAadhaarNumber());
		if(!optObj.isPresent()) {
			Account account = obj.getAccount();
			account.setCustomer(customer);
			customer.setAccounts(Arrays.asList(account));
			Customer new_cust = custRepo.save(customer);
			result= String.format("successfully created customer with id: %d and account number: %d" , new_cust.getCustomerId(), new_cust.getAccounts().get(0).getAccountNumber());
		}
		else{
			Customer existingCust = optObj.get();
			if(existingCust.isNetBankingEnabled()) {
				throw new AlreadyExistsException("Please login to create a new account");
			}
			else {
//				customer.setCustomerId(existingCust.getCustomerId());
//				customer.set
				Account account = obj.getAccount();
				account.setCustomer(existingCust);
				existingCust.getAccounts().add(account);
//				customer.setAccounts(existingCust.getAccounts());
				Customer new_cust = custRepo.save(existingCust);
				result= String.format("successfully created account with account number: %d for customer id: %d" ,  new_cust.getAccounts().get(new_cust.getAccounts().size()-1).getAccountNumber(), new_cust.getCustomerId()); 
			
			}
		}
		return result;
	}
	
	public Account fetchAccount(long accountNo) throws ResourceNotFoundException, UnauthorizedAccessException 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(UserRole.ROLE_ADMIN.toString()));
		Optional<Account> obj = accountRepo.findById(accountNo);
		if(obj.isPresent()) {
			Account acnt = obj.get();
			if(isAdmin || acnt.getCustomer().getUserName().equals(userDetails.getUsername()))
				return obj.get();
			else
				throw new UnauthorizedAccessException("Account doesn't belong to the user");
		}
		else
			throw new ResourceNotFoundException("Account does not exist");
	}	
	
	@Transactional
	public boolean toggleActivation(long acntNo) throws ResourceNotFoundException, UnauthorizedAccessException {
		Optional<Account> obj = accountRepo.findById(acntNo);
		if(obj.isPresent()) {
			Account acnt = obj.get();
			if(acnt.isActive()) {
//				Calendar cal = Calendar.getInstance();
//				cal.setTime(acnt.getLastTransaction());
//				cal.add(Calendar.DATE, 730);
//				Date current = new Date();
//				if(current.after(cal.getTime())) {
//					accountRepo.toggleActivation(false, acntNo);
//					return true;
//				}
//				else {
//					throw new UnauthorizedAccessException("Customer have a transaction in last two years");
//				}
				accountRepo.toggleActivation(false, acntNo);
				return true;
			}
			else {
				accountRepo.toggleActivation(true, acntNo);
				return true;
			}
			
		}
		else {
			throw new ResourceNotFoundException("Invalid Account Number");
		}
	}
	
}
