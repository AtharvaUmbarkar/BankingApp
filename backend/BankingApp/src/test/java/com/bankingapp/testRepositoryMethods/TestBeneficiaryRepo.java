package com.bankingapp.testRepositoryMethods;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
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
import com.bankingapp.models.Beneficiary;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.BeneficiaryRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.AdminService;
import com.bankingapp.service.BeneficiaryService;
import com.bankingapp.service.CustService;
import com.bankingapp.service.TransactionService;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters=false)
public class TestBeneficiaryRepo 
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
	private AccountRepo accRepo;
	@MockBean
	private BeneficiaryRepo benRepo;
	@MockBean
	private AuthenticationManager authManager;
	@MockBean
	private ModelMapper mockModelMapper;
	
	@Test
	void testSave()
	{
		//Arrange
		Beneficiary ben = new Beneficiary();
		
		ben.setName("Test");
		ben.setAccount(new Account());
		ben.setCustomer(new Customer());
		ben.setNickname("Testing");
		
		//Act
		Mockito.when(benRepo.save(ben)).thenReturn(ben);
		Beneficiary benRes = benRepo.save(ben);
		
		
		//Assert
		System.out.println("Testing the Beneficiary Repository : Save Function");
		assertNotNull(benRes);
		assertThat(benRes.getId()).isNotEqualTo(null);
		assertEquals(ben, benRes);
	}
	
	@Test
	void testFindAll()
	{
		//Arrange
		Beneficiary ben1 = new Beneficiary();
		Beneficiary ben2 = new Beneficiary();
		List<Beneficiary> listBen = new ArrayList<Beneficiary>();
		listBen.add(ben1);
		listBen.add(ben2);
		
		benRepo.save(ben1);
		benRepo.save(ben2);
		
		//Act
		Mockito.when(benRepo.findAll()).thenReturn(listBen);
		List<Beneficiary> listBenRes = benRepo.findAll();
		
		//Assert
		System.out.println("Testing the Beneficiary Repository : FindAll Function");
		assertNotNull(listBenRes);
		assertThat(listBen.size()).isEqualTo(listBenRes.size());
		assertEquals(listBen, listBenRes);
	}

}
