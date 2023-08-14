package com.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.models.Account;
import com.bankingapp.service.AccountService;

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
}
