package com.bankingapp.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bankingapp.exception.InsufficientBalanceException;
import com.bankingapp.exception.InvalidTypeException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Transaction;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.TransactionRepo;
import com.bankingapp.types.TransactionModel;
import com.bankingapp.types.UserRole;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	@Autowired
	TransactionRepo transRepo;
	@Autowired
	AccountRepo accountRepo;
	
	
	@Transactional
	public String withdraw(TransactionModel transactionModel, String userName) throws InsufficientBalanceException, ResourceNotFoundException, InvalidTypeException, UnauthorizedAccessException
	{
		String result="";
		long accountNumber = transactionModel.getSenderAccountNumber();
		
			Account acnt = accountRepo.findById(accountNumber).get();
			if(acnt == null) {
//				result="Sender account does not exist";
				throw new ResourceNotFoundException("Account does not exist");
			}
			else if(!acnt.getCustomer().getUserName().equals(userName)) {
				throw new UnauthorizedAccessException("Account doesn't belong to the user");
			}
			else {
					if(!acnt.isActive()) {
					throw new UnauthorizedAccessException("Account is Deactivated");
				}
				Transaction transaction = transactionModel.getTransaction();
				//acnt.setAccountBalance(100000);
				double new_balance = acnt.getAccountBalance() - transaction.getTxnAmount();
				if(new_balance <= 0.00d) {
					throw new InsufficientBalanceException("Insufficient Balance");
				}
				else if (!acnt.getCustomer().getTransactionPassword().equals(transactionModel.getTransactionPassword())) {
					throw new InvalidTypeException("Invalid Transaction Password");
				}
				else {
					int rowsAffected = accountRepo.updateBalance(new_balance, accountNumber);
					if(rowsAffected > 0) {
						transaction.setSenderAccount(acnt);
						transaction.setSenderBalance(new_balance);
						transaction.setTxnStatus("Successful");
						transaction = transRepo.save(transaction);
						accountRepo.changeLastTxn(transaction.getTxnDate(), acnt.getAccountNumber());
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
	public String deposit(TransactionModel transactionModel, String userName) throws ResourceNotFoundException, InvalidTypeException, UnauthorizedAccessException
	{
		String result="";
		long accountNumber = transactionModel.getReceiverAccountNumber();
	
		Account acnt = accountRepo.findById(accountNumber).get();
		if(acnt == null) {
//			result="Sender account does not exist";
			throw new ResourceNotFoundException("Account does not exist");
		}
		else if(!acnt.getCustomer().getUserName().equals(userName)) {
			throw new UnauthorizedAccessException("Account doesn't belong to the user");
		}
		else {
			if(!acnt.isActive()) {
				throw new UnauthorizedAccessException("Account is Deactivated");
			}
			Transaction transaction = transactionModel.getTransaction();
			//acnt.setAccountBalance(100000);
			double new_balance = acnt.getAccountBalance() + transaction.getTxnAmount();
			
			int rowsAffected = accountRepo.updateBalance(new_balance, accountNumber);
			if(rowsAffected > 0) {
				transaction.setReceiverAccount(acnt);
				transaction.setReceiverBalance(new_balance);
				transaction.setTxnStatus("Successful");
				transaction = transRepo.save(transaction);
				accountRepo.changeLastTxn(transaction.getTxnDate(), acnt.getAccountNumber());
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
	public String fundTransfer(TransactionModel transactionModel, String userName) throws ResourceNotFoundException, InsufficientBalanceException, InvalidTypeException, UnauthorizedAccessException
	{
			Account senderAccount = accountRepo.findById(transactionModel.getSenderAccountNumber()).get();
			Account receiverAccount = accountRepo.findById(transactionModel.getReceiverAccountNumber()).get();
			if((senderAccount != null) && (receiverAccount != null)) {
				if(!senderAccount.getCustomer().getUserName().equals(userName)) {
					throw new UnauthorizedAccessException("Account doesn't belong to customer");
				}
				if(senderAccount.isActive() && receiverAccount.isActive()) {
					Transaction transaction = transactionModel.getTransaction();
					//acnt.setAccountBalance(100000);
					double senderNewBalance = senderAccount.getAccountBalance() - transaction.getTxnAmount();
					double receiverNewBalance = receiverAccount.getAccountBalance() + transaction.getTxnAmount();
					if(senderNewBalance <= 0.00d) {
	//					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
						throw new InsufficientBalanceException("Insufficient Balance");
					}
					else if (!transactionModel.getTransactionPassword().equals(senderAccount.getCustomer().getTransactionPassword())) {
						throw new InvalidTypeException("Invalid Transaction Password");
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
							return ("Transaction is successful with transaction id: " + transaction.getTxnId());	
						}
						else {
							return ("Unable to process the request!");
						}
					}
				}
				else {
					throw new UnauthorizedAccessException("Sender or receiver account is not active");
				}
			}
			else {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sender's or receiver's account doesn't exist");
				if (senderAccount == null) {
					throw new ResourceNotFoundException("Sender's Account number Incorrect or doesn't exist");
				}
				if (receiverAccount == null) {
					throw new ResourceNotFoundException("Receiver's Account Number Incorrect or doesn't exist");
				}
			return "";
			}
	}
	
	public List<Object[]> getLatestTransactions(long accountNumber) throws ResourceNotFoundException, UnauthorizedAccessException
	{
		Optional<Account> acc = accountRepo.findById(accountNumber);
		if (!acc.isPresent()) {
			throw new ResourceNotFoundException("Account not Present");
		}
		else {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			boolean isAdmin = authentication.getAuthorities().stream()
					.anyMatch(authority -> authority.getAuthority().equals(UserRole.ROLE_ADMIN.toString()));
			if(isAdmin || acc.get().getCustomer().getUserName().equals(userDetails.getUsername())) {
				return transRepo.getLatestTransactionForAccount(accountNumber);
			}
			else {
				throw new UnauthorizedAccessException("Account doesn't belong to customer");				
			}
		}
		
	}
	
	public List<Object[]> getAccountStatement(long accountNumber, Date from, Date to) throws ResourceNotFoundException, InvalidTypeException, UnauthorizedAccessException{
		
		Optional<Account> acc = accountRepo.findById(accountNumber);
		if (!acc.isPresent()) {
			throw new ResourceNotFoundException("Account not Present");
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(UserRole.ROLE_ADMIN.toString()));
		if(!isAdmin && !acc.get().getCustomer().getUserName().equals(userDetails.getUsername())) {
			throw new UnauthorizedAccessException("Account doesn't belong to customer");
		}
				
		Date creationDate = acc.get().getAccountCreationDate();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(creationDate);
		cal.add(Calendar.DATE, -1);
		Date createDate = cal.getTime();
				
		Date currentdate = new Date();;
		
		if (from.before(createDate)) {
			throw new InvalidTypeException("Start date cannot be before the account creation date");
		}
		else if (to.after(currentdate)) {
			throw new InvalidTypeException("End date cannot be after today");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(to);
		c.add(Calendar.DAY_OF_MONTH, 1);
		return transRepo.getAccountStatement(accountNumber, from, c.getTime());
	}
}
