package com.bankingapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.bankingapp.models.Admin;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AdminRepo;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.AdminService;
import com.bankingapp.service.CustService;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.UserRole;

@RestController
@CrossOrigin("*")
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
	AuthenticationManager adminAuthenticationManager;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	AdminRepo adminRepo;
	
	@PostMapping("SignupAdmin")
	public AdminDTO signUp(@RequestBody LoginModel u) throws Exception
	{
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		Admin admin = new Admin();
		admin.setEmailId("admin@gmial.cinm");
		admin.setMobileNumber("1234567890");
		admin.setName("aaaa");
		admin.setUserName(u.getUsername());
		admin.setLoginPassword(e.encode(u.getPassword()));
		admin.setTitle("MRr");
		adminRepo.save(admin);		
		final UserDetails userDetails = adminService.loadUserByUsername(u.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		AdminDTO adminDTO =  modelMapper.map(adminRepo.findByUserName(u.getUsername()),AdminDTO.class);
		adminDTO.setToken(token);
		return adminDTO;
	}
	
	
	@PostMapping("LoginAdmin")
	public AdminDTO validateAdmin(@RequestBody LoginModel u) throws Exception
	{
		authenticate(u.getUsername(), u.getPassword());
		System.out.println("reached here");
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
	public boolean toggleUser(@RequestParam("custId") int custId) throws ResourceNotFoundException {
		return custService.toggleUser(custId);
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
			System.out.println("admin auth cp1");
			List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(UserRole.ROLE_ADMIN.toString()));
			adminAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password, authorities));
		}catch(DisabledException e) {
			System.out.println("DISABLED!");
//			throw new Exception("USER_DISABLED",e);
		}catch(BadCredentialsException e) {
			System.out.println("BAD CREDS!!!");
			throw new BadCredentialsException("BAD CREDS!");
//			throw new Exception("INVALID_CREDENTIALS", e);
		} catch (AuthenticationException e) {
			System.out.println("AUTH FAILED!!!");
			System.out.println(e.getMessage());
			throw new Exception("Authentication failed!");
		}
	}
	
}
