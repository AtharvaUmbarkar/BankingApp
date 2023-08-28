package com.bankingapp.testRepositoryMethods;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.bankingapp.config.JwtTokenUtil;
import com.bankingapp.models.Account;
import com.bankingapp.models.Admin;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.AdminRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.AdminService;
import com.bankingapp.service.BeneficiaryService;
import com.bankingapp.service.CustService;
import com.bankingapp.service.TransactionService;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters=false)
public class TestAdminRepo 
{
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
	private AdminRepo adminRepo;
	@MockBean
	private CustomerRepo custRepo;
	@MockBean
	private AccountRepo accRepo;
	@MockBean
	private AuthenticationManager authManager;
	@MockBean
	private ModelMapper mockModelMapper;
	
	@Test
	void testSave()
	{
		//Arrange
		Admin admin = new Admin();
		
		admin.setName("Test");
		admin.setEmailId("admin@gmail.com");
		admin.setMobileNumber("7878987890");
		admin.setUserName("admin");
		admin.setLoginPassword("admin@1234");
		
		//Act
		Mockito.when(adminRepo.save(admin)).thenReturn(admin);
		Admin adminRes = adminRepo.save(admin);
		
		
		//Assert
		System.out.println("Testing the Admin Repository : Save Function");
		assertNotNull(adminRes);
		assertEquals(admin, adminRes);
	}
	
	@Test
	void testFindAll()
	{
		//Arrange
		Admin admin1 = new Admin();
		Admin admin2 = new Admin();
		List<Admin> listAdmin = new ArrayList<Admin>();
		listAdmin.add(admin1);
		listAdmin.add(admin2);
		
		adminRepo.save(admin1);
		adminRepo.save(admin2);
		
		//Act
		Mockito.when(adminRepo.findAll()).thenReturn(listAdmin);
		List<Admin> listAdminRes = adminRepo.findAll();
		
		//Assert
		System.out.println("Testing the Admin Repository : FindAll Function");
		assertNotNull(listAdminRes);
		assertThat(listAdmin.size()).isEqualTo(listAdminRes.size());
		assertEquals(listAdmin, listAdminRes);
	}
}
