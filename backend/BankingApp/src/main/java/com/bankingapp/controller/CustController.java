package com.bankingapp.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.models.Account;
import com.bankingapp.config.JwtTokenUtil;
import com.bankingapp.dto.AccountDTO;
import com.bankingapp.dto.CustomerDTO;
import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.InvalidTypeException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.service.CustService;
import com.bankingapp.types.ForgotPasswordModel;
import com.bankingapp.types.ChangePasswordModel;
import com.bankingapp.types.ChangeUserNameModel;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;

@RestController
@CrossOrigin("http://localhost:3000")
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
	
	//no longer needed
	@PostMapping("/saveCustomer")
	public Customer saveCustomer(@RequestBody Customer cust)
	{
		System.out.println(cust);
		Customer c=custService.saveCustomer(cust);
		return c;
	}
	
//	@PostMapping("/Login")
//	public CustomerDTO validateCustomer(@RequestBody LoginModel u) throws UnauthorizedAccessException, ResourceNotFoundException
//	{
//		return modelMapper.map(custService.validateCustomer(u), CustomerDTO.class);
//	}
	
	@PostMapping("/Login")
//	@ResponseBody
	public CustomerDTO validateCustomer(@RequestBody LoginModel u) throws Exception
	{
//		System.out.println("reached here");
		authenticate(u.getUsername(), u.getPassword());
		final UserDetails userDetails = custService.loadUserByUsername(u.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
//		System.out.println(token);
		 CustomerDTO custDTO =  modelMapper.map(custRepo.findByUserName(u.getUsername()),CustomerDTO.class);
		 custDTO.setToken(token);
		 return custDTO;
		 
	}
	
	@GetMapping("/fetchAccounts")
	public List<AccountDTO> fetchAccounts(@RequestHeader(value="Authorization", required=true) String bearerToken) throws ResourceNotFoundException
	{
		String userName = jwtTokenUtil.getUsernameFromToken(bearerToken.substring(7));
		return custService.fetchAccounts(userName).stream().map(acnt -> modelMapper.map(acnt, AccountDTO.class)).collect(Collectors.toList());
	}
	// To be tested for exception
	@PutMapping("/netBankingRegistration")
	public String netbankingreg(@RequestBody NetBankingModel nb) throws AlreadyExistsException, ResourceNotFoundException
	{
		return custService.netbankingreg(nb);
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
	
	//not implementing frontend fn
	@PutMapping("/forgotUserName")
	public String changeUserName(@RequestBody ChangeUserNameModel obj) throws ResourceNotFoundException, InvalidTypeException {
		return custService.changeUserName(obj);
	}
	
	@GetMapping("/fetchUser")
	public Customer fetchUser(@RequestParam("customerId") int custId) throws ResourceNotFoundException{
		return custService.fetchUser(custId);
	}
	
	@GetMapping("/getCustomerAndAccountDetails/{id}")
	public List<Object> getCustomerAndAccountDetails(@PathVariable("id") int id)
	{
		return custService.getCustomerAndAccountDetails(id);
	}
	
	public void authenticate(String userName, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		}catch(DisabledException e) {
			throw new Exception("USER_DISABLED",e);
		}catch(BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
