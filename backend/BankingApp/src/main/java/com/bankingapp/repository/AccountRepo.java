package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankingapp.models.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
	
	@Query("select account.accountNumber from Account account where account.customer.customerId=?1")
	public List<Integer> findByAccountNumber(int custId);
	
	@Modifying
	@Query("update Account account set account.accountBalance = ?1 where account.accountNumber = ?2")
	public int updateBalance(double balance, long accountNumber);

	
}
