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
import com.bankingapp.models.Transaction;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.AdminRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.repository.TransactionRepo;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.AdminService;
import com.bankingapp.service.BeneficiaryService;
import com.bankingapp.service.TransactionService;
import com.bankingapp.service.CustService;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters=false)
public class TestTransactionRepo 
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
	private TransactionRepo txnRepo;
	@MockBean
	private AuthenticationManager authManager;
	@MockBean
	private ModelMapper mockModelMapper;
	
	@Test
	void testSave()
	{
		//Arrange
		Transaction txn = new Transaction();
		
		txn.setSenderAccount(new Account());
		txn.setReceiverAccount(new Account());
		txn.setTxnAmount(500);
		txn.setTxnDate(new Date());
		txn.setTxnType("IMPS");
		txn.setUserRemarks("Fund");
		
		//Act
		Mockito.when(txnRepo.save(txn)).thenReturn(txn);
		Transaction txnRes = txnRepo.save(txn);
		
		
		//Assert
		System.out.println("Testing the Transaction Repository : Save Function");
		assertNotNull(txnRes);
		assertEquals(txn, txnRes);
	}
	
	@Test
	void testFindAll()
	{
		//Arrange
		Transaction txn1 = new Transaction();
		Transaction txn2 = new Transaction();
		List<Transaction> listTxn = new ArrayList<Transaction>();
		listTxn.add(txn1);
		listTxn.add(txn2);
		
		txnRepo.save(txn1);
		txnRepo.save(txn2);
		
		//Act
		Mockito.when(txnRepo.findAll()).thenReturn(listTxn);
		List<Transaction> listTxnRes = txnRepo.findAll();
		
		//Assert
		System.out.println("Testing the Transaction Repository : FindAll Function");
		assertNotNull(listTxnRes);
		assertThat(listTxn.size()).isEqualTo(listTxnRes.size());
		assertEquals(listTxn, listTxnRes);
	}
}
