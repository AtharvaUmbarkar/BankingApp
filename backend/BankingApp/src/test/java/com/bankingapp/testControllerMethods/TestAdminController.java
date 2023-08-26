package com.bankingapp.testControllerMethods;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.bankingapp.config.JwtTokenUtil;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.AdminRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.AdminService;
import com.bankingapp.service.BeneficiaryService;
import com.bankingapp.service.CustService;
import com.bankingapp.service.TransactionService;
import com.bankingapp.types.NetBankingModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters=false)
public class TestAdminController 
{
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private JwtTokenUtil jwtToken;
	@MockBean
	private CustService customerService;
	@MockBean
	private AccountService accountService;
	@MockBean
	private TransactionService transService;
	@MockBean
	private AdminService adminService;
	@MockBean
	private BeneficiaryService bService;
	@MockBean
	private CustomerRepo customerRepo;
	@MockBean
	private AccountRepo accRepo;
	@MockBean 
	private AdminRepo adminRepo;
	
	@MockBean
	private AuthenticationManager authManager;
	ObjectMapper mapper = new ObjectMapper()
			.findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	
	@Test
	public void testAllCustomers() throws Exception
	{
		List<Customer> outputCustomer = new ArrayList<Customer>();
		Customer cust1 = new Customer();
		Customer cust2 = new Customer();
		outputCustomer.add(cust1);
		outputCustomer.add(cust2);
		
		Mockito.when(adminService.allCustomers()).thenReturn(outputCustomer);
		System.out.println("Admin fetch all customers testing.....");
		
		
		
	}
	
	@Test
	public void testSearchCustomersByUsername() throws Exception
	{
		List<Customer> outputCustomer = new ArrayList<Customer>();
		Customer cust1 = new Customer();
		Customer cust2 = new Customer();
		outputCustomer.add(cust1);
		outputCustomer.add(cust2);
		
		Mockito.when(adminService.searchCustomersByUsername(ArgumentMatchers.any())).thenReturn(outputCustomer);
		System.out.println("Admin fetch all customers testing.....");		
		
	}
}
