package com.bankingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankingapp.models.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	Optional<Customer> findByUserName(String userName);
	Optional<Customer> findByAadhaarNumber(String aadhaarNumber);
	
	@Modifying
	@Query("update Customer c set c.userName = ?1, c.loginPassword = ?2, c.transactionPassword = ?3, c.netBankingEnabled = true where c.customerId = ?4")
	public int setUserName(String userName, String lPassword, String tPassword, int customerId);
	

}

