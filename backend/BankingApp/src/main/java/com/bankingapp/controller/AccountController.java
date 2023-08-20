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

import com.bankingapp.models.Account;
import com.bankingapp.service.AccountService;
import com.bankingapp.types.CustomerAndAccountModel;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/createAccount")
	public String createAccount(@RequestBody Account account, @RequestParam("userName") String userName) {
		return accountService.createAccount(account, userName);
	}
	
	@PostMapping("/createFirstAccount")
	public String firstAccount(@RequestBody CustomerAndAccountModel obj) {
		return accountService.firstAccount(obj);
	}
	
	@GetMapping("/fetchAccount")
	public ResponseEntity<Object> fetchAccount(@RequestParam("accountNo") long accountNo){
		Account account = accountService.fetchAccount(accountNo);
		if(account == null) {
			return new ResponseEntity<>("Account does not exist", HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(account, HttpStatus.OK);
		}
	}
}