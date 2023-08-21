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

import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Admin;
import com.bankingapp.models.Customer;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.AdminService;
import com.bankingapp.service.CustService;
import com.bankingapp.types.ChangePasswordModel;
import com.bankingapp.types.ChangeUserNameModel;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;

@RestController
@CrossOrigin("http://localhost:3000")
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	AccountService acntService;
	
	
	@PostMapping("/LoginAdmin")
	public ResponseEntity<Object> validateCustomer(@RequestBody LoginModel u)
	{
		Admin admin = adminService.validateAdmin(u);
		if(admin == null) {
			return new ResponseEntity<>("Invalid Credidentials", HttpStatus.UNAUTHORIZED);
		}
		else {
			return new ResponseEntity<>(admin, HttpStatus.OK);
		}
	}
	
	@GetMapping("/fetch/AllCustomers")
	public List<Customer> allCustomers() throws NoDataFoundException{
		return adminService.allCustomers();
	}
	
	@PutMapping("toggle/Activation")
	public boolean toggleActivation(@RequestParam("acntNo") long acntNo) throws ResourceNotFoundException {
		return acntService.toggleActivation(acntNo);
	}

}
