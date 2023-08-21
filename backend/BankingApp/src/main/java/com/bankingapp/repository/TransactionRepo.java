package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.bankingapp.models.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer>{
}
