package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.bankingapp.models.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer>{
	
	//********** Code added for getting list of transactions of a account
	@Query("SELECT t FROM Transaction t WHERE t.senderAccount.accountNumber = ?1")
	List<Transaction> getAllTransactions(long accountNumber);
}
