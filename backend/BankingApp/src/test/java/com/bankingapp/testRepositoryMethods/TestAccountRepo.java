package com.bankingapp.testRepositoryMethods;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
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
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.AdminService;
import com.bankingapp.service.BeneficiaryService;
import com.bankingapp.service.CustService;
import com.bankingapp.service.TransactionService;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters=false)
public class TestAccountRepo 
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
	private AuthenticationManager authManager;
	@MockBean
	private ModelMapper mockModelMapper;
	
	@Test
	void testSave()
	{
		//Arrange
		Account account = new Account();
		Customer cust = new Customer();
		
		account.setAccountBalance(1000);
		account.setAccountCreationDate(new Date());
		account.setAccountType("Savings");
		account.setActive(true);
		account.setCustomer(cust);
		account.setDebitCardAvailed(true);
		
		//Act
		Mockito.when(accRepo.save(account)).thenReturn(account);
		Account accountRes = accRepo.save(account);
		
		
		//Assert
		System.out.println("Testing the Account Repository : Save Function");
		assertNotNull(accountRes);
		assertThat(accountRes.getAccountNumber()).isNotEqualTo(null);
		assertEquals(account, accountRes);
	}
	
	@Test
	void testFindAll()
	{
		//Arrange
		Account account1 = new Account();
		Account account2 = new Account();
		List<Account> listAccount = new ArrayList<Account>();
		listAccount.add(account1);
		listAccount.add(account2);
		
		accRepo.save(account1);
		accRepo.save(account2);
		
		//Act
		Mockito.when(accRepo.findAll()).thenReturn(listAccount);
		List<Account> listAccountRes = accRepo.findAll();
		
		//Assert
		System.out.println("Testing the Account Repository : FindAll Function");
		assertNotNull(listAccountRes);
		assertThat(listAccount.size()).isEqualTo(listAccountRes.size());
		assertEquals(listAccount, listAccountRes);
	}
}
