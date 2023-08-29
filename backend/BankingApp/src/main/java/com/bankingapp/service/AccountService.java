package com.bankingapp.service;

import java.util.Arrays;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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
import com.bankingapp.models.Beneficiary;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.BeneficiaryRepo;
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
	@Autowired
	private BeneficiaryRepo benRepo;
	@Autowired
	ModelMapper modelMapper;
	
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
			Beneficiary ben = new Beneficiary();
			ben.setName(customer.getFirstName()+" "+customer.getLastName()+"(self)");
			ben.setNickname(customer.getFirstName()+" "+customer.getLastName());
			ben.setCustomer(customer);
			ben.setAccount(account);
			benRepo.save(ben);
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
			Beneficiary ben = new Beneficiary();
			ben.setName(customer.getFirstName()+" "+customer.getLastName()+"(self)");
			ben.setNickname(customer.getFirstName()+" "+customer.getLastName());
			ben.setCustomer(customer);
			ben.setAccount(account);
			benRepo.save(ben);
			result = "Account successfully created wth Account No: " + acnt.getAccountNumber();
			
		}
		return result;
	}
	
	@Transactional
	public String firstAccount(Account account, Customer customer) throws AlreadyExistsException
	{
		String result="";
		Optional<Customer> optObj = custRepo.findByAadhaarNumber(customer.getAadhaarNumber());
		if(!optObj.isPresent()) {
			account.setCustomer(customer);
			customer.setAccounts(Arrays.asList(account));
			Customer newCust = custRepo.save(customer);
			Account newAccount = newCust.getAccounts().get(0);
			Beneficiary ben = new Beneficiary();
			ben.setName(newCust.getFirstName()+" "+newCust.getLastName()+"(self)");
			ben.setNickname(newCust.getFirstName()+" "+newCust.getLastName());
			ben.setCustomer(newCust);
			ben.setAccount(newAccount);
			benRepo.save(ben);
			result= String.format("successfully created customer with id: %d and account number: %d" , newCust.getCustomerId(), newAccount.getAccountNumber());
		}
		else{
			Customer existingCust = optObj.get();
			account.setCustomer(existingCust);
			existingCust.getAccounts().add(account);
			Customer newCust = custRepo.save(existingCust);
			Account newAccount = newCust.getAccounts().get(newCust.getAccounts().size()-1);
			Beneficiary ben = new Beneficiary();
			ben.setName(newCust.getFirstName()+" "+newCust.getLastName()+"(self)");
			ben.setNickname(newCust.getFirstName()+" "+newCust.getLastName());
			ben.setCustomer(newCust);
			ben.setAccount(newAccount);
			benRepo.save(ben);
			result= String.format("successfully created account with account number: %d for customer id: %d" ,  newAccount.getAccountNumber(), newCust.getCustomerId());
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
