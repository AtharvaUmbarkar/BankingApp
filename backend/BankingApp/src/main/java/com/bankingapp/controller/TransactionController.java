package com.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.exception.InsufficientBalanceException;
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
	public String withdraw(@RequestBody TransactionModel transactionModel) throws InsufficientBalanceException
	{
		return tService.withdraw(transactionModel);
	}
	
	@PostMapping("/save/deposit")
	public String deposit(@RequestBody TransactionModel transactionModel)
	{
		return tService.deposit(transactionModel);
	}
	
	@PostMapping("/save/fundTransfer")
	public ResponseEntity<String> fundTransfer(@RequestBody TransactionModel transactionModel)
	{
		return tService.fundTransfer(transactionModel);
	}
	
	@GetMapping("/getLatestTransactions")
	public ResponseEntity<List<Object[]>> getLatestTransactions(@RequestParam long accountNumber){
		return new ResponseEntity<>(tService.getLatestTransactions(accountNumber), HttpStatus.OK);
	}
	
	@GetMapping("/getAccountStatement")
	public ResponseEntity<List<Object[]>> getAccountStatement(@RequestParam long accountNumber, @RequestParam int month, @RequestParam int year){
		return new ResponseEntity<>(tService.getAccountStatement(accountNumber, month, year), HttpStatus.OK);
	}
	
}
