package com.bankingapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankingapp.exception.NoDataFoundException;
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
public class AdminService {
	@Autowired
	AdminRepo adminRepo;
	@Autowired
	CustomerRepo custRepo;
	
	@Transactional
	public Admin validateAdmin(LoginModel loginUser)
	{
		Admin admin = null;
		Optional<Admin> objt = adminRepo.findById(loginUser.getUsername());
		if (objt.isPresent())
		{
			admin = objt.get();
			if (loginUser.getPassword().equals(admin.getLoginPassword())) {
				return admin;
			}
		}
		return null;
	}
	
	public List<Customer> allCustomers() throws NoDataFoundException{
		List<Customer> allCust =  custRepo.findAll();
		if(allCust.isEmpty()) {
			throw new NoDataFoundException("No customers found");
		}
		else {
			return allCust;
		}
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

}
