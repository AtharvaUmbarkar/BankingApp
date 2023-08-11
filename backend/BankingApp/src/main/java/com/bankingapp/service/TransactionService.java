package com.bankingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.models.Transaction;
import com.bankingapp.repository.TransactionRepo;

@Service
public class TransactionService {
	@Autowired
	TransactionRepo transRepo;
	public Transaction saveTransaction(Transaction transaction)
	{
		return transRepo.save(transaction);
	}
}
