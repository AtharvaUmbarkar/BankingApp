package com.bankingapp.interfaces;

import java.util.List;
import java.util.Optional;

import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Admin;
import com.bankingapp.models.Customer;
import com.bankingapp.types.LoginModel;

public interface AdminServiceInterface {
	public Admin validateAdmin(LoginModel loginUser) throws ResourceNotFoundException, UnauthorizedAccessException;
	public List<Customer> allCustomers() throws NoDataFoundException;
}
