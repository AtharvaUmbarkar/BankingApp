package com.bankingapp.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.config.JwtTokenUtil;
import com.bankingapp.dto.AccountDTO;
import com.bankingapp.dto.CustomerDTO;
import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.InvalidTypeException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.service.CustService;
import com.bankingapp.types.ForgotPasswordModel;
import com.bankingapp.types.ChangePasswordModel;
import com.bankingapp.types.ChangeUserNameModel;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;
import com.bankingapp.types.UserRole;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
public class CustController {
	@Autowired
	CustService custService;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	CustomerRepo custRepo;

	
	@PostMapping("/Login")
//	@ResponseBody
	@Transactional
	public CustomerDTO validateCustomer(@RequestBody LoginModel u) throws UnauthorizedAccessException 
	{
//		System.out.println("reached here");
		authenticate(u.getUsername(), u.getPassword());
		custService.changeLastLogin(u.getUsername());
		final UserDetails userDetails = custService.loadUserByUsername(u.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
//		System.out.println(token);
		 CustomerDTO custDTO =  modelMapper.map(custService.findByUserName(u.getUsername()),CustomerDTO.class);
		 custDTO.setToken(token);
		 return custDTO;
	}
	
	@GetMapping("/fetchAccounts")
	public List<AccountDTO> fetchAccounts(@RequestHeader(value="Authorization", required=true) String bearerToken) throws ResourceNotFoundException
	{
		String userName = jwtTokenUtil.getUsernameFromToken(bearerToken.substring(7));
		return custService.fetchAccounts(userName).stream().map(acnt -> modelMapper.map(acnt, AccountDTO.class)).collect(Collectors.toList());
	}
	
	@PutMapping("/netBankingRegistration")
	public String netBankinRreg(@RequestBody NetBankingModel nb) throws AlreadyExistsException, ResourceNotFoundException
	{
		return custService.netBankingReg(nb);
	}
	
	@PutMapping("/forgotPassword")
	public String forgotPassword(@RequestBody ChangePasswordModel obj) throws ResourceNotFoundException, InvalidTypeException 
	{
		return custService.forgotPassword(obj);
	}
	
	@PutMapping("/changePassword")
	public String changePassword(@RequestBody ChangePasswordModel obj) throws ResourceNotFoundException, InvalidTypeException 
	{
		return custService.changePassword(obj);
	}
	
	@PutMapping("/forgotUserName")
	public String changeUserName(@RequestBody ChangeUserNameModel obj) throws ResourceNotFoundException, UnauthorizedAccessException 
	{
		return custService.changeUserName(obj);
	}
		
	@GetMapping("/fetchUser")
	public Customer fetchUser(@RequestParam("customerId") int custId) throws ResourceNotFoundException
	{
		return custService.fetchUser(custId);
	}
	
	@GetMapping("/getCustomerAndAccountDetails/{id}")
	public List<Object> getCustomerAndAccountDetails(@PathVariable("id") int id)
	{
		return custService.getCustomerAndAccountDetails(id);
	}
	
	@Transactional
	public void authenticate(String userName, String password) throws UnauthorizedAccessException
	{
		try {
			List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(UserRole.ROLE_USER.toString()));
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password, authorities));
		}catch(BadCredentialsException e) {
			if(e.getMessage().contains("Password")) {
				custService.increaseAttempts(userName);
			}
			else {
				throw new UnauthorizedAccessException(e.getMessage());
			}
		}catch(LockedException e) {
			throw new UnauthorizedAccessException(e.getMessage());
		}
	}

}
