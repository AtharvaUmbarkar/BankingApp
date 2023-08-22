package com.bankingapp.controller;

import java.util.List;

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
	
	//no longer needed
	@PostMapping("/saveCustomer")
	public Customer saveCustomer(@RequestBody Customer cust)
	{
		System.out.println(cust);
		Customer c=custService.saveCustomer(cust);
		return c;
	}
	
	@PostMapping("/Login")
	public Customer validateCustomer(@RequestBody LoginModel u) throws UnauthorizedAccessException, ResourceNotFoundException
	{
		return custService.validateCustomer(u);
//		if(response.equals("Invalid user")) {
//			return new ResponseEntity<>("Invalid User Name", HttpStatus.NOT_FOUND);
//		}
//		else if(response.equals("Login failed")) {
//			return new ResponseEntity<>("Incorrect Password", HttpStatus.UNAUTHORIZED);
//		}
//		else {
//			Customer cust = custRepo.findByUserName(loginUser.getUsername());
//		}
	}
	
	@GetMapping("/fetchAccounts/")
	public List<Integer> fetchAccounts(@RequestParam("username") String username) throws ResourceNotFoundException, NoDataFoundException
	{
		return custService.fetchAccounts(username);
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
