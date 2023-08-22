package com.bankingapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.interfaces.AdminServiceInterface;
import com.bankingapp.models.Account;
import com.bankingapp.models.Admin;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.AdminRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.types.ChangePasswordModel;
import com.bankingapp.types.ChangeUserNameModel;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;

import jakarta.transaction.Transactional;

@Service
public class AdminService implements AdminServiceInterface{
	@Autowired
	AdminRepo adminRepo;
	@Autowired
	CustomerRepo custRepo;
	
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

}
