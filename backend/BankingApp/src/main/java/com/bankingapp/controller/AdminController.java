package com.bankingapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.AdminDTO;
import com.bankingapp.dto.CustomerDTO;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Admin;
import com.bankingapp.models.Customer;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.AdminService;
import com.bankingapp.service.CustService;
import com.bankingapp.types.ForgotPasswordModel;
import com.bankingapp.types.ChangeUserNameModel;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;

@RestController
@CrossOrigin("http://localhost:3000")
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	AccountService acntService;
	@Autowired
	CustService custService;
	@Autowired
	ModelMapper modelMapper;
	
	
	@PostMapping("/LoginAdmin")
	public AdminDTO validateAdmin(@RequestBody LoginModel u) throws ResourceNotFoundException, UnauthorizedAccessException
	{
		return modelMapper.map(adminService.validateAdmin(u),AdminDTO.class);
	}
	
	@GetMapping("/fetch/AllCustomers")
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

}
