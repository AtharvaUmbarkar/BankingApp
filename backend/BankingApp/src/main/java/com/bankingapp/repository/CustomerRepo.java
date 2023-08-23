package com.bankingapp.repository;

import java.util.Date;
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
	
	@Modifying
	@Query("update Customer c set c.loginPassword = ?1, c.noFailedAttemps = 0, c.unLocked = true where c.userName = ?2")
	public int changeLoginPassword(String password, String userName);
	
	@Modifying
	@Query("update Customer c set c.transactionPassword = ?1 where c.userName = ?2")
	public int changeTransactionPassword(String password, String userName);
	
	@Modifying
	@Query("update Customer c set c.lastLogin = ?1, c.noFailedAttemps = ?2, c.unLocked=?3 where c.userName = ?4")
	public int changeLastLogin(Date date, int attempts, boolean unLocked, String userName);
	
	@Modifying
	@Query("update Customer c set c.userName = ?1 where c.aadhaarNumber = ?2")
	public int changeUserName(String userName, String aadhaarNumber);
	
	@Modifying
	@Query("update Customer c set c.unLocked = ?1 where c.userName = ?2")
	public int toggleUser(boolean unLocked, String userName);

	@Query("SELECT c from Customer c WHERE c.userName LIKE ?1")
	public List<Customer> searchByUsername(String query);

	@Query("SELECT c, a from Customer c JOIN Account a on a.customer.customerId = c.customerId WHERE c.customerId = ?1")
	public List<Object> getCustomerAndAccountDetails(int id);
}


