package com.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.models.Account;
import com.bankingapp.service.AccountService;
import com.bankingapp.types.CustomerAndAccountModel;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
public class AccountController {
	@Autowired
	private AccountService accountService;
	//To be tested for exception
	@PostMapping("/createAccount")
	public String createAccount(@RequestBody Account account, @RequestParam("userName") String userName) throws ResourceNotFoundException
	{
		return accountService.createAccount(account, userName);
	}
	//To be tested for Exception
	@PostMapping("/createFirstAccount")
	public String firstAccount(@RequestBody CustomerAndAccountModel obj) throws AlreadyExistsException
	{
		return accountService.firstAccount(obj);
	}
	
	@GetMapping("/fetchAccount")
	public Account fetchAccount(@RequestParam("accountNo") long accountNo) throws NoDataFoundException{
		return accountService.fetchAccount(accountNo);
	}
}
