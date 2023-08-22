package com.bankingapp.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.AccountDTO;
import com.bankingapp.dto.CustomerDTO;
import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.InvalidTypeException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Customer;
import com.bankingapp.service.CustService;
import com.bankingapp.types.ChangePasswordModel;
import com.bankingapp.types.ChangeUserNameModel;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;

@RestController
@CrossOrigin("http://localhost:3000")
public class CustController {
	@Autowired
	CustService custService;
	@Autowired
	ModelMapper modelMapper;
	
	//no longer needed
	@PostMapping("/saveCustomer")
	public Customer saveCustomer(@RequestBody Customer cust)
	{
		System.out.println(cust);
		Customer c=custService.saveCustomer(cust);
		return c;
	}
	
	@PostMapping("/Login")
	public CustomerDTO validateCustomer(@RequestBody LoginModel u) throws UnauthorizedAccessException, ResourceNotFoundException
	{
		return modelMapper.map(custService.validateCustomer(u), CustomerDTO.class);
	}
	
	@GetMapping("/fetchAccounts")
	public List<AccountDTO> fetchAccounts(@RequestParam("username") String username) throws ResourceNotFoundException, NoDataFoundException
	{
		return custService.fetchAccounts(username).stream().map(acnt -> modelMapper.map(acnt, AccountDTO.class)).collect(Collectors.toList());
	}
	// To be tested for exception
	@PutMapping("/netBankingRegistration")
	public String netbankingreg(@RequestBody NetBankingModel nb) throws AlreadyExistsException, ResourceNotFoundException
	{
		return custService.netbankingreg(nb);
	}
	
	@PutMapping("/forgotPassword")
	public String changePassword(@RequestBody ChangePasswordModel obj, @RequestParam("userName") String userName) throws ResourceNotFoundException, InvalidTypeException 
	{
		return custService.changePassword(obj, userName);
	}
	
	@PutMapping("/forgotUserName")
	public String changeUserName(@RequestBody ChangeUserNameModel obj) throws ResourceNotFoundException, InvalidTypeException {
		return custService.changeUserName(obj);
	}
	
	@GetMapping("/fetchUser")
	public Customer fetchUser(@RequestParam("customerId") int custId) throws ResourceNotFoundException{
		return custService.fetchUser(custId);
	}

}
