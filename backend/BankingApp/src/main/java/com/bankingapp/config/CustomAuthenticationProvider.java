package com.bankingapp.config;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Customer;

public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	CustomerRepo custRepo;
	@Autowired
	PasswordEncoder bcryptEncoder;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("start of auth");
		String userName = authentication.getName();
		Optional<Customer> optCust = custRepo.findByUserName(userName);
		if(optCust.isPresent()) {
			Customer cust = optCust.get();
			if(!cust.isUnLocked()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(cust.getLastLogin());
				cal.add(Calendar.DATE, 1);
				Date current = new Date();
				if(cal.getTime().after(current)) {
					throw new LockedException("Exceeded 3 login attemps, please change your password to login");
				}
			}
			if(bcryptEncoder.matches(authentication.getCredentials().toString(), cust.getLoginPassword())) {
//				custRepo.changeLastLogin(new Date(),0,true,userName); //also set 0 failed attempts
				return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials());
			}
			else {
				int noAttempts = cust.getNoFailedAttemps()+1;
				if(noAttempts>2) {
					//update time, attempts, unLocked
//					custRepo.changeLastLogin(new Date(),0,false,userName);
					throw new LockedException("3 attempts failed, your account have be locked for 1 day");
				}
				else {
					//update time and attempts, unlocked
//					custRepo.changeLastLogin(new Date(),noAttempts,true,userName);
					throw new BadCredentialsException(String.format("Wrong Password, %d more attempts remaining", 3-noAttempts));
 				}
			}
		}
		else {
			throw new BadCredentialsException("Wrong Username");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
