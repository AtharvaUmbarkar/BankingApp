package com.bankingapp.service;

import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public String createAccount(Account account, String userName) {
		String result="";
		Customer customer;
		Optional<Customer> obj = custRepo.findByUserName(userName);
		if(!obj.isPresent()) {
			result = "Customer not found";
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
	public String firstAccount(CustomerAndAccountModel obj) {
		String result="";
		Customer customer = obj.getCustomer();
		Optional<Customer> optObj = custRepo.findByAadhaarNumber(customer.getAadhaarNumber());
		if(!optObj.isPresent()) {
			Account account = obj.getAccount();
			account.setCustomer(customer);
			customer.setAccount(Arrays.asList(account));
			Customer new_cust = custRepo.save(customer);
			result= String.format("successfully created customer with id: %d and account number: %d" , new_cust.getCustomerId(), new_cust.getAccount().get(0).getAccountNumber());
		}
		else{
			Customer existingCust = optObj.get();
			if(existingCust.isNetBankingEnabled()) {
				result = "please login to create a new account";
			}
			else {
				customer.setCustomerId(existingCust.getCustomerId());
//				customer.set
				Account account = obj.getAccount();
				account.setCustomer(customer);
				existingCust.getAccount().add(account);
				customer.setAccount(existingCust.getAccount());
				Customer new_cust = custRepo.save(customer);
				result= String.format("successfully created account with account number: %d for customer id: %d" ,  new_cust.getAccount().get(new_cust.getAccount().size()-1).getAccountNumber(), new_cust.getCustomerId()); 
			
			}
		}
		return result;
	}
	
	public Account fetchAccount(long accountNo) {
		Optional<Account> obj = accountRepo.findById(accountNo);
		if(obj.isPresent())
			return obj.get();
		else
			return null;
	}
	
	
	
	
	
	
	
	
}
