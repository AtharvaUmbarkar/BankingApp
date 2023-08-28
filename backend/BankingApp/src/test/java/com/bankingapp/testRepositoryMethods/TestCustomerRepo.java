package com.bankingapp.testRepositoryMethods;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;

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

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters=false)
public class TestCustomerRepo 
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
	private CustomerRepo custRepo;
	@MockBean
	private AdminRepo adminRepo;
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
		Customer cust = new Customer();
		cust.setTitle("Mr");
		cust.setFirstName("Riya");
		cust.setFatherName("Sanjay");
		cust.setLastName("Gupta");
		cust.setAadhaarNumber("123878986789");
		cust.setDateOfBirth(LocalDate.of(2001, 10, 10));
		cust.setEmailId("riya@gmail.com");
		cust.setGrossAnnualIncome(2400000);
		cust.setMiddleName("Sanjay");
		cust.setMobileNumber("7867564534");
		cust.setNetBankingEnabled(true);
		cust.setOccupation("SDE");
		cust.setPermAddressLine1("Krishnakunj");
		cust.setPermAddressLine2("Bhagwan Nagar");
		cust.setPermCity("Nagar");
		cust.setPermLandmark("Girna Tank");
		cust.setPermPincode("425002");
		cust.setPermState("Maharashtra");
		cust.setSourceOfIncome("Job");
		
		//Act
		Mockito.when(custRepo.save(cust)).thenReturn(cust);
		Customer custRes = custRepo.save(cust);
		
		
		//Assert
		System.out.println("Testing the Customer Repository : Save Function");
		assertNotNull(custRes);
		assertThat(custRes.getCustomerId()).isNotEqualTo(null);
		assertEquals(cust, custRes);
	}
	
	@Test
	void testFindAll()
	{
		//Arrange
		Customer cust1 = new Customer();
		Customer cust2 = new Customer();
		List<Customer> listCust = new ArrayList<Customer>();
		listCust.add(cust1);
		listCust.add(cust2);
		
		custRepo.save(cust1);
		custRepo.save(cust2);
		
		//Act
		Mockito.when(custRepo.findAll()).thenReturn(listCust);
		List<Customer> listCustRes = custRepo.findAll();
		
		//Assert
		System.out.println("Testing the Customer Repository : FindAll Function");
		assertNotNull(listCustRes);
		assertThat(listCust.size()).isEqualTo(listCustRes.size());
		assertEquals(listCust, listCustRes);
	}
}
