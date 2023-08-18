package com.bankingapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.BeneficiaryService;
import com.bankingapp.service.CustService;
import com.bankingapp.service.TransactionService;
import com.bankingapp.types.LoginModel;

@RunWith(SpringRunner.class)
@WebMvcTest
class TestCustomerController {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CustService customerService;
	@MockBean
	private AccountService accountService;
	@MockBean
	private TransactionService transService;
	@MockBean
	private BeneficiaryService bService;
	@MockBean
	private CustomerRepo customerRepo;
	
	@Test
	public void testCustomerLogin() throws Exception
	{
		LoginModel login = new LoginModel();
		login.setUsername("shradha");
		login.setPassword("smk@123456");
		//login.setEmail("");
		
		Mockito.when(customerService.validateCustomer(login)
				).thenReturn("Login Success");
		
		System.out.println("Testing login unit....");
		
		assertEquals("Login Success",customerService.validateCustomer(login));
}

}