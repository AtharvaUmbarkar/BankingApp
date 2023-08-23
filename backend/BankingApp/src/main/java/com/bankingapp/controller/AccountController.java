package com.bankingapp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.AccountDTO;
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
	@Autowired ModelMapper modelMapper;
	//To be tested for exception
	@PostMapping("/createAccount")
	public String createAccount(@RequestBody AccountDTO accountDto, @RequestParam("userName") String userName) throws ResourceNotFoundException
	{
		Account account = modelMapper.map(accountDto, Account.class);
		return accountService.createAccount(account, userName);
	}
	//To be tested for Exception
	//add dto to model
	@PostMapping("/createFirstAccount")
	public String firstAccount(@RequestBody CustomerAndAccountModel obj) throws AlreadyExistsException
	{
		return accountService.firstAccount(obj);
	}
	
	@GetMapping("/fetchAccount")
	public AccountDTO fetchAccount(@RequestParam("accountNo") long accountNo) throws ResourceNotFoundException{
		return modelMapper.map(accountService.fetchAccount(accountNo), AccountDTO.class);
	}
}
