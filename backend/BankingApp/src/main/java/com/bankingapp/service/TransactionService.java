package com.bankingapp.service;

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
	public String saveTransaction(TransactionModel transaction)
	{
		String result="";
		if(transaction.getTxnType().equals("Withdraw")) {
			Optional<Account> obj = accountRepo.findById(transaction.getSenderAccount());
			if(!obj.isPresent()) {
				result="Sender account does not exist";
			}
			else {
				Account acnt = obj.get();
				//acnt.setAccountBalance(100000);
				int new_balance = acnt.getAccountBalance() - transaction.getTxnAmount();
				if(new_balance < 0) {
					result="Insufficient balance";
				}
				else {
					Transaction new_transaction = new Transaction();
					new_transaction.setSenderAccount(acnt);
					new_transaction.setTxnType(transaction.getTxnType());
					new_transaction.setTxnAmount(transaction.getTxnAmount());
					new_transaction.setUserRemarks(transaction.getUserRemarks());
					new_transaction.setSenderBalance(new_balance);
					new_transaction.setTxnStatus("Successful");
					transRepo.save(new_transaction);
					acnt.setAccountBalance(new_balance);
					result = "Transaction is successful with transaction id: " + new_transaction.getTxnId();
				}
			}
		}
		
		return result;
	}
}
