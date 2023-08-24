package com.bankingapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.config.JwtTokenUtil;
import com.bankingapp.dto.AdminDTO;
import com.bankingapp.dto.CustomerDTO;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AdminRepo;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.AdminService;
import com.bankingapp.service.CustService;
import com.bankingapp.types.LoginModel;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	AccountService acntService;
	@Autowired
	CustService custService;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	AdminRepo adminRepo;
	

	@PostMapping("LoginAdmin")
	public AdminDTO validateAdmin(@RequestBody LoginModel u) throws Exception
	{
		System.out.println("reached here");
//		authenticate(u.getUsername(), u.getPassword());
		final UserDetails userDetails = adminService.loadUserByUsername(u.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		System.out.println(token);
		 AdminDTO adminDTO =  modelMapper.map(adminRepo.findByUserName(u.getUsername()),AdminDTO.class);
		 adminDTO.setToken(token);
		 return adminDTO;
	}
	
	@GetMapping("fetch/AllCustomers")
	public List<CustomerDTO> allCustomers() throws NoDataFoundException{
		return adminService.allCustomers().stream().map(cust -> modelMapper.map(cust, CustomerDTO.class)).collect(Collectors.toList());
	}
	
	@PutMapping("toggle/Activation")
	public boolean toggleActivation(@RequestParam("acntNo") long acntNo) throws ResourceNotFoundException, UnauthorizedAccessException {
		return acntService.toggleActivation(acntNo);
	}
	
	@PutMapping("toggle/user")
	public boolean toggleUser(@RequestParam("userName") String userName) throws ResourceNotFoundException {
		return custService.toggleUser(userName);
	}

	@GetMapping("searchCustomerByUsername")
	public List<Customer> searchCustomersByUsername(@RequestParam("query") String query) throws NoDataFoundException{
		return adminService.searchCustomersByUsername(query);
	}
	
	@GetMapping("getTransactionStats")
	public Object getTransactionStats() throws NoDataFoundException{
		return adminService.getTransactionStats();
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
