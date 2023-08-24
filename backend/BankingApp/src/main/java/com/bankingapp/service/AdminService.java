package com.bankingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.interfaces.AdminServiceInterface;
import com.bankingapp.models.Admin;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AdminRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.repository.TransactionRepo;
import com.bankingapp.types.LoginModel;

import jakarta.transaction.Transactional;

@Service
public class AdminService implements AdminServiceInterface, UserDetailsService {
	@Autowired
	AdminRepo adminRepo;
	@Autowired
	CustomerRepo custRepo;
	@Autowired
	TransactionRepo transRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Admin admin = adminRepo.findByUserName(userName).get();
		if(admin!=null) {
			return new org.springframework.security.core.userdetails.User(admin.getUserName(), admin.getLoginPassword(), new ArrayList());
		}
		else {
			throw new UsernameNotFoundException("User name not found");
		}
	}
	
	
	
	@Transactional
	public Admin validateAdmin(LoginModel loginUser) throws ResourceNotFoundException, UnauthorizedAccessException
	{
		Admin admin = null;
		Optional<Admin> objt = adminRepo.findById(loginUser.getUsername());
		if (objt.isPresent())
		{
			admin = objt.get();
			if (loginUser.getPassword().equals(admin.getLoginPassword())) {
				return admin;
			}
			else {
				throw new UnauthorizedAccessException("Invalid password");
			}
		}
		throw new ResourceNotFoundException("Admin not found");
	}
	
	public List<Customer> allCustomers(){
		return  custRepo.findAll();
	}
	
	public List<Customer> searchCustomersByUsername(String query) throws NoDataFoundException{
		List<Customer> allCust =  custRepo.searchByUsername("%"+query+"%");
		if(allCust.isEmpty()) {
			throw new NoDataFoundException("No customers found");
		}
		else {
			return allCust;
		}
	}

	public Object getTransactionStats() throws NoDataFoundException{
		Object stats = transRepo.getTransactionStats();
		if(stats == null) {
			throw new NoDataFoundException("Data not found");
		} else {
			return stats;
		}
	}
	
}
