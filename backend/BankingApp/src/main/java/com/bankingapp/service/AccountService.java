package com.bankingapp.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.CustomerRepo;

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
}
