package com.bankingapp.interfaces;


import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;

public interface AccountServiceInterface {
	public String createAccount(Account account, String userName) throws ResourceNotFoundException;
	public String firstAccount(Account account, Customer customer) throws AlreadyExistsException;
	public Account fetchAccount(long accountNo) throws ResourceNotFoundException, UnauthorizedAccessException ;
	public boolean toggleActivation(long acntNo) throws ResourceNotFoundException, UnauthorizedAccessException; 
}
