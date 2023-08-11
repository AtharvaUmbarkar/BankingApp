package com.bankingapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.models.Customer;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.types.LoginModel;

@Service
public class CustService {
	@Autowired
	CustomerRepo custRepo;
	public Customer saveCustomer(Customer cust)
	{
		Customer obj=custRepo.save(cust);
		return obj;
	}
	
	public String validateCustomer(LoginModel loginuser)
	{
		String result = "";
		Customer cust = null;
		Optional<Customer> objt = custRepo.findById(loginuser.getUsername());
		if (objt.isPresent())
		{
			cust = objt.get();
		}
//		Customer cust=custRepo.findById(u.getUsername()).get();
		
		if (cust==null)
		{
			result = "Invalid user";
		}
		else
		{
			if (loginuser.getPassword().equals(cust.getPassword()))
			{
				result = "Login Success";
			}
			else
			{
				result = "Login Failed";
			}
		}
		return result;
	}

}
