package com.bankingapp.interfaces;

import java.util.List;
import java.util.Optional;

import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.InvalidTypeException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;
import com.bankingapp.types.ChangePasswordModel;
import com.bankingapp.types.ChangeUserNameModel;
import com.bankingapp.types.NetBankingModel;

public interface CustomerServiceInterface {
	public void increaseAttempts(String userName) throws UnauthorizedAccessException;
	public void changeLastLogin(String userName) throws UnauthorizedAccessException;
	public List<Account> fetchAccounts(String username) throws ResourceNotFoundException;
	public String netBankingReg(NetBankingModel nb) throws AlreadyExistsException, ResourceNotFoundException;
	public String forgotPassword(ChangePasswordModel obj ) throws ResourceNotFoundException, InvalidTypeException;
	public String changePassword(ChangePasswordModel obj ) throws ResourceNotFoundException, InvalidTypeException;
	public String changeUserName(ChangeUserNameModel obj) throws ResourceNotFoundException, UnauthorizedAccessException;
	public Customer fetchUser(int custId) throws ResourceNotFoundException;
	public boolean toggleUser(int custId) throws ResourceNotFoundException;
	public List<Object> getCustomerAndAccountDetails(int custId);
	public Optional<Customer> findByUserName(String username);
}
