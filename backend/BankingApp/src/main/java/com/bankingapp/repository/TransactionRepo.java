package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.bankingapp.models.Transaction;
import com.bankingapp.types.TransactionModel;

public interface TransactionRepo extends JpaRepository<Transaction, Integer>{

	@Query("SELECT t, t.senderAccount.accountNumber as senderAccountNumber, t.receiverAccount.accountNumber as receiverAccountNumber from Transaction t WHERE t.senderAccount.accountNumber =?1 OR t.receiverAccount.accountNumber = ?1 ORDER BY t.txnDate DESC LIMIT 10")
	List<Object[]> getLatestTransactionForAccount(long accountNumber);

	@Query("SELECT t, t.senderAccount.accountNumber as senderAccountNumber, t.receiverAccount.accountNumber as receiverAccountNumber from Transaction t WHERE (t.senderAccount.accountNumber =?1 OR t.receiverAccount.accountNumber = ?1) AND (MONTH(t.txnDate) =?2 AND YEAR(t.txnDate) =?3) ORDER BY t.txnDate DESC LIMIT 10")
	List<Object[]> getAccountStatementForMonthAndYear(long accountNumber, int month, int year);
}
