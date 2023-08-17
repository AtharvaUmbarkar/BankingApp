package com.bankingapp.controller;

import java.util.ArrayList;

import org.hibernate.mapping.List;
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
	@PostMapping("/save/withdraw")
	public String withdraw(@RequestBody TransactionModel transactionModel)
	{
		return tService.withdraw(transactionModel);
	}
	
	@PostMapping("/save/fundTransfer")
	public String fundTransfer(@RequestBody TransactionModel transactionModel)
	{
		return tService.fundTransfer(transactionModel);
	}
}
