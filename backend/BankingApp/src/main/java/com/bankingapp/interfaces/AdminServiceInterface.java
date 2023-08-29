package com.bankingapp.interfaces;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Admin;
import com.bankingapp.models.Customer;
import com.bankingapp.types.LoginModel;

public interface AdminServiceInterface {
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
	public Admin validateAdmin(LoginModel loginUser) throws ResourceNotFoundException, UnauthorizedAccessException;
	public List<Customer> allCustomers();
	public List<Customer> searchCustomersByUsername(String query) throws NoDataFoundException;
	public Object getTransactionStats() throws NoDataFoundException;
	public Admin findById(String userName) throws UnauthorizedAccessException;
}
