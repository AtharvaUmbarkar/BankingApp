package com.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.models.Customer;
import com.bankingapp.service.CustService;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;

@RestController
@CrossOrigin("http://localhost:3000")
public class CustController {
	@Autowired
	CustService custService;
	
	@PostMapping("/saveCustomer")
	public Customer saveCustomer(@RequestBody Customer cust)
	{
		System.out.println(cust);
		Customer c=custService.saveCustomer(cust);
		return c;
	}
	
	@PostMapping("/Login")
	public String validateCustomer(@RequestBody LoginModel u)
	{
		return custService.validateCustomer(u);
	}
	
	@GetMapping("/fetchAccounts/{username}")
	public List<Integer> fetchAccounts(@PathVariable("username") String username)
	{
		List<Integer> accountList = custService.fetchAccounts(username);
		return accountList;
	}
	
	@PostMapping("/netBankingRegistration")
	public String netbankingreg(@RequestBody NetBankingModel nb)
	{
		return custService.netbankingreg(nb);
	}
	

}
