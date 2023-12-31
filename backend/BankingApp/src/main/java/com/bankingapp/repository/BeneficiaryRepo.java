package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankingapp.models.Beneficiary;

@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary,Integer> {
	@Query("SELECT b FROM Beneficiary b  WHERE b.customer.userName = ?1")
	List<Beneficiary> getAllBeneficiaries(String userName);
	
	@Modifying
	@Query("DELETE FROM Beneficiary b WHERE b.id = ?1")
	int deleteBeneficiary(int id);
}
