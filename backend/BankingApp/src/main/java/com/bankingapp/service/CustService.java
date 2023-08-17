package com.bankingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;

@Service
public class CustService {
	@Autowired
	CustomerRepo custRepo;
	@Autowired
	AccountRepo accRepo;
	public Customer saveCustomer(Customer cust)
	{
		Customer obj=custRepo.save(cust);
		return obj;
	}
	
	public String validateCustomer(LoginModel loginUser)
	{
		String result = "";
		Customer cust = null;
		Optional<Customer> objt = custRepo.findByUserName(loginUser.getUsername());
		if (objt.isPresent())
		{
			cust = objt.get();
		}
//		Customer cust=custRepo.findById(u.getUsername()).get();
		
		if (cust==null)
		{
			result = "Invalid user";
		}
		else
		{
			if (loginUser.getPassword().equals(cust.getLoginPassword()))
			{
				result = "Login Success";
			}
			else
			{
				result = "Login Failed";
			}
		}
		return result;
	}
	
	public List<Integer> fetchAccounts(String username)
	{
		Optional<Customer> obj = custRepo.findByUserName(username);
		int custId = (obj.get()).getCustomerId();
		return accRepo.findByAccountNumber(custId);
	}
	
	public String netbankingreg(NetBankingModel nb)
	{
		String result = "";
		Optional<Account> obj = accRepo.findByAccountNum(nb.getAccountNumber());
		if (obj.isPresent())
		{
			Customer cust = obj.get().getCustomer();

			if (cust.isNetBankingEnabled())
			{
				result = "User already exists";
			}
			else
			{
				if (!((nb.getLoginPassword()).equals(nb.getConfirmLoginPassword())))
				{
					result = "Login Password and Confirm Login Password not same";
				}
				else if (!((nb.getTransactionPassword()).equals(nb.getConfirmTransactionPassword())))
				{
					result = "Transaction Password and Confirm Transaction Password are not same";
				}
				else
				{
				cust.setUserName(nb.getUserName());
				cust.setLoginPassword(nb.getLoginPassword());
				cust.setTransactionPassword(nb.getTransactionPassword());
				cust.setNetBankingEnabled(true);
				//cust.validateotp();
				}
			}
		}
		else
		{
			result = "Account does not exist, Open an account first";
		}
		return result;
	}

}
