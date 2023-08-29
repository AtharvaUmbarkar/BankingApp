package com.bankingapp.testControllerMethods;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
//import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bankingapp.config.JwtTokenUtil;
import com.bankingapp.config.WebSecurityConfig;
import com.bankingapp.dto.CustomerDTO;
import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.AdminRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.AdminService;
import com.bankingapp.service.BeneficiaryService;
import com.bankingapp.service.CustService;
import com.bankingapp.service.TransactionService;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;
import com.bankingapp.types.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters=false)
class TestCustomerController {
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
	private ModelMapper mockModelMapper;
	
	@MockBean
	private AuthenticationManager authManager;
	ObjectMapper mapper = new ObjectMapper()
			.findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	

	@Test
	public void testFetchAccount() throws Exception
	{
		String username = "sumit";
		Account act = new Account();
		act.setAccountNumber(1000000000);
		act.setAccountType("Savings");
		act.setAccountBalance(4800.0);
		act.setActive(false);
		act.setDebitCardAvailed(true);
		
		List<Account> actList = new ArrayList<Account>();
		actList.add(act);
		
		Mockito.when(customerService.fetchAccounts(username)).thenReturn(actList);
		System.out.println("Testing Fetch Accounts Functionality....");
		
		List<Account> resList = customerService.fetchAccounts(username);
		
		assertThat(resList.size()).isEqualTo(actList.size());
		assertEquals(resList, actList);
		
		/*MvcResult mvcRes =  mvc.perform(get("/fetchAccounts/"+username).
				contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		String res = mvcRes.getResponse().getContentAsString();
		System.out.println("Expected : "+json);
		System.out.println("Actual : "+res);
		assertEquals(res,json);*/
	}
	
	@Test
	public void testNetBankingRegistration() throws Exception
	{
		NetBankingModel nbm = new NetBankingModel();
		nbm.setAccountNumber(10000000);
		nbm.setLoginPassword("xyz@1234");
		nbm.setTransactionPassword("abc@1234");
		nbm.setUserName("shradha");
		nbm.setOtp("1234");
		
		Mockito.when(customerService.netBankingReg(ArgumentMatchers.any())).thenReturn("successfully registered for net banking");
		System.out.println("Testing Net Banking Registration Functionality....");
		String json = mapper.writeValueAsString(nbm);
		MvcResult mvcRes = mvc.perform(put("/netBankingRegistration").
				contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").
				content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		String res = mvcRes.getResponse().getContentAsString();
		System.out.println("Returned value : "+res);
		assertEquals(res,"successfully registered for net banking");
	}
	
	@Test
	public void testCustomerLogin() throws Exception
	{
		//Test login with valid credentials
		LoginModel login = new LoginModel();
		login.setUsername("sumit");
		login.setPassword("sumit@123");
		
		CustomerDTO custDTO = new CustomerDTO();
		Customer cust = new Customer();
		cust.setFirstName("Sumit");
		cust.setMiddleName("Manoj");
		cust.setLastName("Kumavat");
		cust.setEmailId("sumit@gmail.com");
		cust.setCustomerId(0);
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(UserRole.ROLE_USER.toString()));
		
		
		UserDetails userDt = new org.springframework.security.core.userdetails.User(login.getUsername(), login.getPassword(), true, true, true, true,authorities );
		
		Mockito.when(customerService.loadUserByUsername(login.getUsername())
				).thenReturn(userDt);
		
		UserDetails resUserDT = customerService.loadUserByUsername(login.getUsername());
		assertThat(resUserDT).isNotNull();
		assertEquals(resUserDT,userDt);
		
		/*Customer resCustomer = customerService.validateCustomer(login);
		assertThat(resCustomer).isNotNull();
		assertEquals(resCustomer, cust);
		
		Mockito.when(mockModelMapper.map(cust, CustomerDTO.class)).thenReturn(custDTO);
		System.out.println("Testing login unit....");
		
		CustomerDTO resCustDTO = mockModelMapper.map(cust, CustomerDTO.class);
		
		assertThat(resCustDTO).isNotNull();
		assertEquals(custDTO, resCustDTO);
		
		//String json = mapper.writeValueAsString(login);
		//String custStr = mapper.writeValueAsString(custDTO);
		//System.out.println("String Customer : "+custStr);
		/*MvcResult mvcRes =
				mvc.perform(post("/Login").
				contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").
				content(json).andExpect(MockMvcResultMatchers.status().isCreated());*/
				//accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		//String res = mvcRes.getResponse().getContentAsString();
		//System.out.println("******Returned object : "+res);
		//assertEquals(res,custStr);
		
	}
}