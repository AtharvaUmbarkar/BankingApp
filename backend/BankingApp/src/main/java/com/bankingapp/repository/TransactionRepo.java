package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.models.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer>{

}
