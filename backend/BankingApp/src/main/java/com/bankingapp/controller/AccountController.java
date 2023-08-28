package com.bankingapp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.config.JwtTokenUtil;
import com.bankingapp.dto.AccountDTO;
import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Account;
import com.bankingapp.service.AccountService;
import com.bankingapp.types.CustomerAndAccountModel;
import com.bankingapp.types.UserRole;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired ModelMapper modelMapper;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	//To be tested for exception
	@PostMapping("/createAccount")
	public String createAccount(@RequestBody AccountDTO accountDto, @RequestParam String username, @RequestHeader(value="Authorization", required=true) String bearerToken) throws ResourceNotFoundException
	{
		String userName = jwtTokenUtil.getRolesFromToken(bearerToken.substring(7)).contains(UserRole.ROLE_ADMIN.toString()) ? 
				username : jwtTokenUtil.getUsernameFromToken(bearerToken.substring(7));
		Account account = modelMapper.map(accountDto, Account.class);
		return accountService.createAccount(account, userName);
	}
	
	@PostMapping("/createAccoutUsingId")
	public String createAccountUsingId(@RequestBody AccountDTO accountDto, @RequestParam int custId) throws ResourceNotFoundException
	{
		Account account = modelMapper.map(accountDto, Account.class);
		return accountService.createAccountUsingId(account, custId);
	}
	//To be tested for Exception
	//add dto to model
	@PostMapping("/createFirstAccount")
	public String firstAccount(@RequestBody CustomerAndAccountModel obj) throws AlreadyExistsException
	{
		return accountService.firstAccount(obj);
	}
	
	@GetMapping("/fetchAccount")
	public AccountDTO fetchAccount(@RequestParam("accountNo") long accountNo) throws ResourceNotFoundException, UnauthorizedAccessException{
		return modelMapper.map(accountService.fetchAccount(accountNo), AccountDTO.class);
	}
}
