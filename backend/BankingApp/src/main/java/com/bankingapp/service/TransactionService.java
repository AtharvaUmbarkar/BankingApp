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
	public String saveWithdrawTransaction(Transaction transaction, long accountNumber)
	{
		String result="";
		
			Optional<Account> obj = accountRepo.findById(accountNumber);
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
					transaction.setSenderAccount(acnt);
					transaction.setSenderBalance(new_balance);
					transaction.setTxnStatus("Successful");
					transRepo.save(transaction);
					acnt.setAccountBalance(new_balance);
					result = "Transaction is successful with transaction id: " + transaction.getTxnId();
				}
			}
		
		
		return result;
	}
}
