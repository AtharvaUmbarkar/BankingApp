package com.bankingapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan({"com"})
@SpringBootTest(classes= com.bankingapp.TestCustomerController.class)
class BankingappApplicationTests {

	@Test
	void contextLoads() {
	}

}
