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

import com.bankingapp.models.Account;
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
	public ResponseEntity<Object> validateCustomer(@RequestBody LoginModel u)
	{
		Customer cust = custService.validateCustomer(u);
		if(cust == null) {
			return new ResponseEntity<>("Invalid Credidentials", HttpStatus.UNAUTHORIZED);
		}
		else {
			return new ResponseEntity<>(cust, HttpStatus.OK);
		}
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
	
	@GetMapping("/fetchAccounts/{username}")
	public List<Account> fetchAccounts(@PathVariable("username") String username)
	{
		List<Account> accountList = custService.fetchAccounts(username);
		return accountList;
	}
	
	@PutMapping("/netBankingRegistration")
	public String netbankingreg(@RequestBody NetBankingModel nb)
	{
		return custService.netbankingreg(nb);
	}
	
	@PutMapping("/forgotPassword")
	public String changePassword(@RequestBody ChangePasswordModel obj, @RequestParam("userName") String userName) {
		return custService.changePassword(obj, userName);
	}
	
	@PutMapping("/forgotUserName")
	public String changeUserName(@RequestBody ChangeUserNameModel obj) {
		return custService.changeUserName(obj);
	}
	
	@GetMapping("/fetchUser")
	public ResponseEntity<Object> fetchUser(@RequestParam("customerId") int custId){
		Customer cust = custService.fetchUser(custId);
		if(cust == null) {
			return new ResponseEntity<>("Invalid Customer ID", HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(cust, HttpStatus.OK);
		}
	}
	
	@GetMapping("/getCustomerAndAccountDetails/{id}")
	public List<Object> getCustomerAndAccountDetails(@PathVariable("id") int id)
	{
		return custService.getCustomerAndAccountDetails(id);
	}

}
