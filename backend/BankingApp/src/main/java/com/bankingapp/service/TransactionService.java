package com.bankingapp.service;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.models.Account;
import com.bankingapp.models.Transaction;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.TransactionRepo;
import com.bankingapp.types.TransactionModel;

@Service
public class TransactionService {
	@Autowired
	TransactionRepo transRepo;
	@Autowired
	AccountRepo accountRepo;
	public String saveWithdrawTransaction(TransactionModel transactionModel)
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
				int new_balance = acnt.getAccountBalance() - transaction.getTxnAmount();
				if(new_balance < 0) {
					result="Insufficient balance";
				}
				else {
					transaction.setSenderAccount(acnt);
					transaction.setSenderBalance(new_balance);
					transaction.setTxnStatus("Successful");
					transRepo.save(transaction);
					acnt.setAccountBalance(new_balance);
					accountRepo.save(acnt);
					result = "Transaction is successful with transaction id: " + transaction.getTxnId();
				}
			}
		
		
		return result;
	}
	
	
	public String saveFundTransaction(TransactionModel transactionModel)
	{
		String result="";
		
			Optional<Account> obj1 = accountRepo.findById(transactionModel.getSenderAccountNumber());
			Optional<Account> obj2 = accountRepo.findById(transactionModel.getReceiverAccountNumber());
			if(obj1.isPresent() && obj2.isPresent()) {
				Account senderAccount = obj1.get();
				Account receiverAccount = obj2.get();
				Transaction transaction = transactionModel.getTransaction();
				//acnt.setAccountBalance(100000);
				int senderNewBalance = senderAccount.getAccountBalance() - transaction.getTxnAmount();
				int receiverNewBalance = receiverAccount.getAccountBalance() + transaction.getTxnAmount();
				if(senderNewBalance < 0) {
					result="Insufficient balance";
				}
				else {
					transaction.setSenderAccount(senderAccount);
					transaction.setReceiverAccount(receiverAccount);
					transaction.setSenderBalance(senderNewBalance);
					transaction.setReceiverBalance(receiverNewBalance);
					transaction.setTxnStatus("Successful");
					transRepo.save(transaction);
					senderAccount.setAccountBalance(senderNewBalance);
					accountRepo.save(senderAccount);
					receiverAccount.setAccountBalance(receiverNewBalance);
					accountRepo.save(receiverAccount);
					result = "Transaction is successful with transaction id: " + transaction.getTxnId();
				}
			}
			else {
				result = "sender's or receiver's account doesn't exist";
			}
		
		
		return result;
	}
}
