package com.bankingapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/save/deposit")
	public String deposit(@RequestBody TransactionModel transactionModel)
	{
		return tService.deposit(transactionModel);
	}
	
	@PostMapping("/save/fundTransfer")
	public String fundTransfer(@RequestBody TransactionModel transactionModel)
	{
		return tService.fundTransfer(transactionModel);
	}
	
	@GetMapping("/getAllTransactions")
	public List<Transaction> getAllTransactions(@RequestParam long accountNo){
		return tService.getAllTransactions(accountNo);
	}
	
	@GetMapping("/getStatementTransactions")
	public List<Transaction> getStatementTransactions(@RequestParam long accountNo, @RequestParam String fromDt, @RequestParam String toDt){
		return tService.getStatementTransactions(accountNo,fromDt,toDt);
	}
}
