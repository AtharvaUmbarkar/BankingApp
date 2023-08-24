package com.bankingapp.interfaces;

import java.util.Date;
import java.util.List;
import com.bankingapp.exception.InsufficientBalanceException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Transaction;
import com.bankingapp.types.TransactionModel;


public interface TransactionServiceInterface {
	public List<Transaction> getAllTransactions(long accountNo) throws ResourceNotFoundException;
	public List<Transaction> getStatementTransactions(long accountNo, String fromDt, String toDt) throws ResourceNotFoundException;
	public String withdraw(TransactionModel transactionModel) throws InsufficientBalanceException, UnauthorizedAccessException;
	public String deposit(TransactionModel transactionModel) throws UnauthorizedAccessException;
	public String fundTransfer(TransactionModel transactionModel) throws ResourceNotFoundException, InsufficientBalanceException, UnauthorizedAccessException;	
	public List<Object[]> getLatestTransactions(long accountNumber) throws ResourceNotFoundException;
	public List<Object[]> getAccountStatement(long accountNumber, Date from, Date to);
}