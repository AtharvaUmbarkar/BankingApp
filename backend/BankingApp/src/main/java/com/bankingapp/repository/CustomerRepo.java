package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingapp.models.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
}
