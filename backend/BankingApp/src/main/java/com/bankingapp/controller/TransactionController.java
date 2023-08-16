package com.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.models.Transaction;
import com.bankingapp.service.TransactionService;
import com.bankingapp.types.TransactionModel;

@RestController
@CrossOrigin("http://localhost:3000")
public class TransactionController 
{
	@Autowired
	TransactionService tService;
	@PostMapping("/save/transaction")
	
	public String saveTransaction(@RequestBody TransactionModel trans)
	{
		return tService.saveTransaction(trans);
	}
}
