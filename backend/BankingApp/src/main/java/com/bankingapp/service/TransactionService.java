package com.bankingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankingapp.models.Account;
import com.bankingapp.models.Transaction;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.TransactionRepo;
import com.bankingapp.types.TransactionModel;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	@Autowired
	TransactionRepo transRepo;
	@Autowired
	AccountRepo accountRepo;
	
	@Transactional
	public String withdraw(TransactionModel transactionModel)
	{
		String result="";
		long accountNumber = transactionModel.getSenderAccountNumber();
		
			Optional<Account> obj = accountRepo.findById(accountNumber);
			if(!obj.isPresent()) {
				result="Sender account does not exist";
			}
			else {
				Account acnt = obj.get();
				Transaction transaction = transactionModel.getTransaction();
				//acnt.setAccountBalance(100000);
				double new_balance = acnt.getAccountBalance() - transaction.getTxnAmount();
				if(new_balance < 0.00d) {
					result="Insufficient balance";
				}
				else {
					int rowsAffected = accountRepo.updateBalance(new_balance, accountNumber);
					if(rowsAffected > 0) {
						transaction.setSenderAccount(acnt);
						transaction.setSenderBalance(new_balance);
						transaction.setTxnStatus("Successful");
						transRepo.save(transaction);
	//					acnt.setAccountBalance(new_balance);
						result = "Transaction is successful with transaction id: " + transaction.getTxnId();
					}
					else {
						result = "unable to process the request";
					}
				}
			}
		
		
		return result;
	}
	
	@Transactional
	public String deposit(TransactionModel transactionModel)
	{
		String result="";
		long accountNumber = transactionModel.getReceiverAccountNumber();
		
			Optional<Account> obj = accountRepo.findById(accountNumber);
			if(!obj.isPresent()) {
				result="Receiver account does not exist";
			}
			else {
				Account acnt = obj.get();
				Transaction transaction = transactionModel.getTransaction();
				//acnt.setAccountBalance(100000);
				double new_balance = acnt.getAccountBalance() + transaction.getTxnAmount();
				
				int rowsAffected = accountRepo.updateBalance(new_balance, accountNumber);
				if(rowsAffected > 0) {
					transaction.setReceiverAccount(acnt);
					transaction.setReceiverBalance(new_balance);
					transaction.setTxnStatus("Successful");
					transRepo.save(transaction);
//					acnt.setAccountBalance(new_balance);
					result = "Transaction is successful with transaction id: " + transaction.getTxnId();
				}
				else {
					result = "unable to process the request";
					
				}
			}
		
		
		return result;
	}
	
	@Transactional
	public ResponseEntity<String> fundTransfer(TransactionModel transactionModel)
	{
			Optional<Account> obj1 = accountRepo.findById(transactionModel.getSenderAccountNumber());
			Optional<Account> obj2 = accountRepo.findById(transactionModel.getReceiverAccountNumber());
			if(obj1.isPresent() && obj2.isPresent()) {
				Account senderAccount = obj1.get();
				Account receiverAccount = obj2.get();
				Transaction transaction = transactionModel.getTransaction();
				//acnt.setAccountBalance(100000);
				double senderNewBalance = senderAccount.getAccountBalance() - transaction.getTxnAmount();
				double receiverNewBalance = receiverAccount.getAccountBalance() + transaction.getTxnAmount();
				if(senderNewBalance < 0.00d) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
				}
				else {
					int rowsAffected1 = accountRepo.updateBalance(senderNewBalance, senderAccount.getAccountNumber());
					int rowsAffected2 = accountRepo.updateBalance(receiverNewBalance, receiverAccount.getAccountNumber());
					if(rowsAffected1>0 && rowsAffected2>0) {
						transaction.setSenderAccount(senderAccount);
						transaction.setReceiverAccount(receiverAccount);
						transaction.setSenderBalance(senderNewBalance);
						transaction.setReceiverBalance(receiverNewBalance);
						transaction.setTxnStatus("Successful");
						transRepo.save(transaction);
						return ResponseEntity.status(HttpStatus.OK).body("Transaction is successful with transaction id: " + transaction.getTxnId());	
					}
					else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to process the request!");
					}
				}
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sender's or receiver's account doesn't exist");
			}
	}
	
	public List<Object[]> getLatestTransactions(long accountNumber){
		return transRepo.getLatestTransactionForAccount(accountNumber);
	}
	
	public List<Object[]> getAccountStatement(long accountNumber, int month, int year){
		return transRepo.getAccountStatementForMonthAndYear(accountNumber, month, year);
	}
}
