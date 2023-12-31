package com.bankingapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bankingapp.models.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer>{

	@Query("SELECT t, t.senderAccount.accountNumber as senderAccountNumber, t.receiverAccount.accountNumber as receiverAccountNumber from Transaction t WHERE t.senderAccount.accountNumber =?1 OR t.receiverAccount.accountNumber = ?1 ORDER BY t.txnDate DESC")
	List<Object[]> getLatestTransactionForAccount(long accountNumber);

	@Query("SELECT t, t.senderAccount.accountNumber as senderAccountNumber, t.receiverAccount.accountNumber as receiverAccountNumber from Transaction t WHERE (t.senderAccount.accountNumber =?1 OR t.receiverAccount.accountNumber = ?1) AND (t.txnDate >= ?2 AND t.txnDate <= ?3) ORDER BY t.txnDate DESC")
	List<Object[]> getAccountStatement(long accountNumber, Date from, Date to);

	@Query("SELECT count(t), sum(t.txnAmount) from Transaction t")
	Object getTransactionStats();
	
}
