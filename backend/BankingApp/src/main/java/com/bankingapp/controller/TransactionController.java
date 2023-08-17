package com.bankingapp.controller;

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
	@PostMapping("/save/withdrawTransaction")
	public String saveWithdrawTransaction(@RequestBody TransactionModel transaction)
	{
		return tService.saveWithdrawTransaction(transaction.getTransaction(), transaction.getAccountNumbers().get(0).longValue());
	}
	
//	@PostMapping("/save/FundTransaction")
//	public String saveFundTransaction(@RequestBody TransactionModel transaction)
//	{
//		List<Long> accountNumbers = transaction.getAccountNumbers();
//		return tService.saveFundTransaction(transaction.getTransaction(), accountNumbers.get(0).longValue());
//	}
}
