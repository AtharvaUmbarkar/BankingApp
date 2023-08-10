package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingapp.models.Beneficiary;

@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary,Integer> {

}
