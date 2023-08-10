package com.bankingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.models.Beneficiary;
import com.bankingapp.repository.BeneficiaryRepo;

@Service
public class BeneficiaryService {
	@Autowired
	BeneficiaryRepo benRepo;
	public Beneficiary saveBeneficiary(Beneficiary ben) {
		return benRepo.save(ben);
	}
}
