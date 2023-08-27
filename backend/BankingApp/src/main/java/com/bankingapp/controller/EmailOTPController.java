package com.bankingapp.controller;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.CustService;
import com.bankingapp.service.EmailService;
import com.bankingapp.types.NetBankingModel;

@RestController
@CrossOrigin("http://localhost:3000")
public class EmailOTPController 
{
	@Autowired
	CustService custService;
	
	@Autowired
	AccountRepo accRepo;
	
	@Autowired
	EmailService emailService;
	
	@PostMapping("/sendOTPEmail")
	public String sendOTPEmail(@RequestBody long accountNumber) throws Exception
	{
		String result = "OTP not sent";
		Optional<Account> obj = accRepo.findById(accountNumber);
		if (obj.isPresent())
		{
			Customer cust = obj.get().getCustomer();
			String otp = generateOTP();
			emailService.sendEmail(cust.getEmailId(), "OTP for BA", "Your OTP : "+otp);
			System.out.println("OTP sent successfully...");
			result = "OTP sent successfully";
		}
		return result;
	}
	
	private String generateOTP()
	{
		//Generate 4 digit OTP
		Random random = new Random();
		int otpValue = 1000 + random.nextInt(9000);
		return String.valueOf(otpValue);
	}
}
