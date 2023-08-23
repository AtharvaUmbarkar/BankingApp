package com.bankingapp.controller;

import java.util.Date;
import java.util.List;

//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.exception.InsufficientBalanceException;
import com.bankingapp.exception.InvalidTypeException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
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
	public String withdraw(@RequestBody TransactionModel transactionModel) throws InsufficientBalanceException, ResourceNotFoundException
	{
		return tService.withdraw(transactionModel);
	}
	
	@PostMapping("/save/deposit")
	public String deposit(@RequestBody TransactionModel transactionModel) throws ResourceNotFoundException
	{
		return tService.deposit(transactionModel);
	}
	// To be tested
	@PostMapping("/save/fundTransfer")
	public String fundTransfer(@RequestBody TransactionModel transactionModel) throws ResourceNotFoundException, InsufficientBalanceException
	{
		return tService.fundTransfer(transactionModel);
	}
	
	@GetMapping("/getLatestTransactions")
	public ResponseEntity<List<Object[]>> getLatestTransactions(@RequestParam long accountNumber) throws ResourceNotFoundException
	{
		return new ResponseEntity<>(tService.getLatestTransactions(accountNumber), HttpStatus.OK);
	}
	
	@GetMapping("/getAccountStatement")
	public List<Object[]> getAccountStatement(@RequestParam long accountNumber, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) throws ResourceNotFoundException, InvalidTypeException
	{
		return tService.getAccountStatement(accountNumber, from, to);
	}
//	
//	
//	@GetMapping("/getAllTransactions")
//	public List<Transaction> getAllTransactions(@RequestParam long accountNo) throws NoDataFoundException, ResourceNotFoundException
//	{
//		return tService.getAllTransactions(accountNo);
//	}
//	// To be tested
//	@GetMapping("/getStatementTransactions") // What is the difference between this method and get account statement method?
//	public List<Transaction> getStatementTransactions(@RequestParam long accountNo, @RequestParam String fromDt, @RequestParam String toDt) throws NoDataFoundException, ResourceNotFoundException
//	{
//		return tService.getStatementTransactions(accountNo,fromDt,toDt);
//	}
}
