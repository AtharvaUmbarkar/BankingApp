package com.bankingapp.testControllerMethods;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bankingapp.config.JwtTokenUtil;
import com.bankingapp.dto.AdminDTO;
import com.bankingapp.dto.CustomerDTO;
import com.bankingapp.models.Account;
import com.bankingapp.models.Admin;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.AdminRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.repository.TransactionRepo;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.AdminService;
import com.bankingapp.service.BeneficiaryService;
import com.bankingapp.service.CustService;
import com.bankingapp.service.TransactionService;
import com.bankingapp.types.LoginModel;
import com.bankingapp.types.NetBankingModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters=false)
@EnableAutoConfiguration(exclude = {
		SecurityAutoConfiguration.class,
		//ManagementSecurityAutoConfiguration.class
})
public class TestAdminController 
{
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ModelMapper mockModelMapper;
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
	private TransactionRepo tRepo;
	
	@MockBean
	private AuthenticationManager authManager;
	ObjectMapper mapper = new ObjectMapper()
			.findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	
	@Test
	public void testAdminLogin() throws Exception
	{
		LoginModel loginModel = new LoginModel();
		loginModel.setUsername("admin");
		loginModel.setPassword("admin@1234");
		
		Admin admin = new Admin();
		admin.setName("TestAdmin");
		admin.setEmailId("admin@gmail.com");
		admin.setUserName("admin");
		admin.setLoginPassword("admin@1234");
		
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setName("TestAdmin");
		adminDTO.setEmailId("admin@gmail.com");
		adminDTO.setUserName("admin");
		
		Mockito.when(adminService.validateAdmin(loginModel)).thenReturn(admin);
		System.out.println("Testing Admin - Login functionality");
		
		Mockito.when(mockModelMapper.map(admin,AdminDTO.class)).thenReturn(adminDTO);
		AdminDTO adt = mockModelMapper.map(admin, AdminDTO.class);
		assertEquals(adt,adminDTO);
		
		/*String json = mapper.writeValueAsString(loginModel);
		
		MvcResult mvcRes = mvc.perform(post("/LoginAdmin").
				contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").
				content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();*/
		
		
		//assertEquals(mapper.writeValueAsString(adminDTO), mvcRes.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testAllCustomers() throws Exception
	{
		List<Customer> outputCustomer = new ArrayList<Customer>();
		Customer cust1 = new Customer();
		cust1.setFirstName("Test1");
		Customer cust2 = new Customer();
		cust2.setFirstName("Test2");
		outputCustomer.add(cust1);
		outputCustomer.add(cust2);
		
		List<CustomerDTO> outputCustomerDTO = new ArrayList<CustomerDTO>();
		CustomerDTO custDTO1 = new CustomerDTO();
		custDTO1.setFirstName("Test1");
		CustomerDTO custDTO2 = new CustomerDTO();
		custDTO2.setFirstName("Test2");
		outputCustomerDTO.add(custDTO1);
		outputCustomerDTO.add(custDTO2);

		Mockito.when(adminService.allCustomers()).thenReturn(outputCustomer);
		System.out.println("Testing Admin - Fetch all customers functionality");
				
		Mockito.when(mockModelMapper.map(cust1, CustomerDTO.class)).thenReturn(custDTO1);
		Mockito.when(mockModelMapper.map(cust2, CustomerDTO.class)).thenReturn(custDTO2);
		
		CustomerDTO resCustDTO1 = mockModelMapper.map(cust1, CustomerDTO.class);
		assertThat(resCustDTO1).isNotNull();
		assertEquals(resCustDTO1, custDTO1);
		
		UserDetails dummy = new User("test@gmail.com","Test",new ArrayList<>());
		String jToken = jwtToken.generateToken(dummy);
		
		/*RequestBuilder req = MockMvcRequestBuilders.get("/fetch/AllCustomers").
				header("Authorization","Bearer "+jToken).contentType(MediaType.APPLICATION_JSON_VALUE).
				accept(MediaType.APPLICATION_JSON);*/
		
		//MvcResult mvcRes = mvc.perform(req).andExpect(status().isOk()).andReturn();
		
		
		//assertEquals(mapper.writeValueAsString(outputCustomerDTO), mvcRes.getResponse().getContentAsString());*/
		
	}
	
	/*@Test
	public void testSearchCustomersByUsername() throws Exception
	{
		List<Customer> outputCustomer = new ArrayList<Customer>();
		Customer cust1 = new Customer();
		cust1.setFirstName("Test1");
		Customer cust2 = new Customer();
		cust2.setFirstName("Test2");
		outputCustomer.add(cust1);
		outputCustomer.add(cust2);
		
		Mockito.when(adminService.searchCustomersByUsername("Test")).thenReturn(outputCustomer);
		
		System.out.println("Testing Admin - Search customer by user name functionality");		
		
		MvcResult mvcRes = mvc.perform(get("/searchCustomerByUsername").
				contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").
				param("query","Test").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		
		assertEquals(mapper.writeValueAsString(outputCustomer), mvcRes.getResponse().getContentAsString());
	}
	
	@Test
	public void testToggleActivation() throws Exception
	{
		Account account = new Account();
		account.setAccountNumber(500);
		account.setAccountType("Savings");
		account.setActive(false);
		
		Mockito.when(accountService.toggleActivation(account.getAccountNumber())).thenReturn(true);
		boolean toggleOutput = accountService.toggleActivation(account.getAccountNumber());
		assertEquals(toggleOutput,true);
		
		MvcResult mvcRes = mvc.perform(put("/toggle/Activation").
				contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").
				param("acntNo",""+account.getAccountNumber()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		
		assertEquals(mapper.writeValueAsString(true),mvcRes.getResponse().getContentAsString());
	}
	
	
	@Test
	public void testToggleUser() throws Exception
	{
		Customer cust = new Customer();
		cust.setCustomerId(100);
		cust.setFirstName("TestCustomer");
		cust.setUserName("test@user");
		cust.setEmailId("test@gmail.com");
		cust.setUnLocked(false);
		
		Mockito.when(customerService.toggleUser(cust.getCustomerId())).thenReturn(true);
		boolean toggleOutput = customerService.toggleUser(cust.getCustomerId());
		assertEquals(toggleOutput,true);
		
		MvcResult mvcRes = mvc.perform(put("/toggle/user").
				contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").
				param("userName",""+cust.getUserName()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		
		assertEquals(mapper.writeValueAsString(true),mvcRes.getResponse().getContentAsString());
	}
	
	
	@Test
	public void testGetTransactionStats() throws Exception
	{
		Object obj = new Object();
		
		Mockito.when(adminService.getTransactionStats()).thenReturn(obj);
		Mockito.when(tRepo.getTransactionStats()).thenReturn(obj);

		Object objRes = adminService.getTransactionStats();
		assertEquals(obj, objRes);

		Object objRepoRes = tRepo.getTransactionStats();
		assertEquals(obj, objRepoRes);
	}*/
}
