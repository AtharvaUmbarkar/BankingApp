package com.bankingapp.interfaces;


import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;
import com.bankingapp.types.CustomerAndAccountModel;

public interface AccountServiceInterface {
	public String createAccount(Account account, String userName) throws ResourceNotFoundException;
	public String firstAccount(CustomerAndAccountModel obj) throws AlreadyExistsException;
	public Account fetchAccount(long accountNo) throws NoDataFoundException ;
	public boolean toggleActivation(long acntNo) throws ResourceNotFoundException; 
}
