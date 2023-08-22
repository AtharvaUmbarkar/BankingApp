package com.bankingapp.interfaces;

import java.util.List;
import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.InvalidTypeException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;
import com.bankingapp.types.ChangePasswordModel;
import com.bankingapp.types.ChangeUserNameModel;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;

public interface CustomerServiceInterface {
	public Customer saveCustomer(Customer cust);
	public Customer validateCustomer(LoginModel loginUser) throws UnauthorizedAccessException, ResourceNotFoundException;
	public List<Account> fetchAccounts(String username) throws ResourceNotFoundException, NoDataFoundException;
	public String netbankingreg(NetBankingModel nb) throws AlreadyExistsException, ResourceNotFoundException;
	public String changePassword(ChangePasswordModel obj, String userName) throws ResourceNotFoundException, InvalidTypeException;
	public String changeUserName(ChangeUserNameModel obj) throws ResourceNotFoundException, InvalidTypeException;
	public Customer fetchUser(int custId) throws ResourceNotFoundException;
}
