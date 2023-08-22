package com.bankingapp.service;

import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.types.CustomerAndAccountModel;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
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
	
	public Account fetchAccount(long accountNo) throws NoDataFoundException 
	{
		Optional<Account> obj = accountRepo.findById(accountNo);
		if(obj.isPresent())
			return obj.get();
		else
			throw new NoDataFoundException("Account does not exist");
	}	
	
	@Transactional
	public boolean toggleActivation(long acntNo) throws ResourceNotFoundException {
		Optional<Account> obj = accountRepo.findById(acntNo);
		if(obj.isPresent()) {
			Account acnt = obj.get(); 
			int rowsAffected = accountRepo.toggleActivation(!acnt.isActive(), acntNo);
			if(rowsAffected > 0)
				return true;
			else
				return false;
		}
		else {
			throw new ResourceNotFoundException("Invalid Account Number");
		}
	}
	
}
