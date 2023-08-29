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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.config.JwtTokenUtil;
import com.bankingapp.exception.InsufficientBalanceException;
import com.bankingapp.exception.InvalidTypeException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Transaction;
import com.bankingapp.service.TransactionService;
import com.bankingapp.types.TransactionModel;

@RestController
@CrossOrigin("http://localhost:3000")
public class TransactionController 
{
	@Autowired
	TransactionService tService;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/save/withdraw")
	public String withdraw(@RequestBody TransactionModel transactionModel, @RequestHeader(value="Authorization", required=true) String bearerToken) throws InsufficientBalanceException, ResourceNotFoundException, InvalidTypeException, UnauthorizedAccessException
	{
		String userName = jwtTokenUtil.getUsernameFromToken(bearerToken.substring(7));
		return tService.withdraw(transactionModel, userName);
	}
	
	@PostMapping("/save/deposit")
	public String deposit(@RequestBody TransactionModel transactionModel, @RequestHeader(value="Authorization", required=true) String bearerToken) throws ResourceNotFoundException, InvalidTypeException, UnauthorizedAccessException
	{
		String userName = jwtTokenUtil.getUsernameFromToken(bearerToken.substring(7));
		return tService.deposit(transactionModel, userName);
	}
	
	@PostMapping("/save/fundTransfer")
	public String fundTransfer(@RequestBody TransactionModel transactionModel, @RequestHeader(value="Authorization", required=true) String bearerToken) throws ResourceNotFoundException, InsufficientBalanceException, InvalidTypeException, UnauthorizedAccessException
	{
		String userName = jwtTokenUtil.getUsernameFromToken(bearerToken.substring(7));
		return tService.fundTransfer(transactionModel, userName);
	}
	
	@GetMapping("/getLatestTransactions")
	public ResponseEntity<List<Object[]>> getLatestTransactions(@RequestParam long accountNumber) throws ResourceNotFoundException, UnauthorizedAccessException
	{
		return new ResponseEntity<>(tService.getLatestTransactions(accountNumber), HttpStatus.OK);
	}
	
	@GetMapping("/getAccountStatement")
	public List<Object[]> getAccountStatement(@RequestParam long accountNumber, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) throws ResourceNotFoundException, InvalidTypeException, UnauthorizedAccessException
	{
		return tService.getAccountStatement(accountNumber, from, to);
	}

}
