package com.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankingapp.models.Beneficiary;
import com.bankingapp.repository.BeneficiaryRepo;

@Service
public class BeneficiaryService {
	@Autowired
	BeneficiaryRepo benRepo;
	public Beneficiary saveBeneficiary(Beneficiary ben) {
		return benRepo.save(ben);
	}
	
	public List<Beneficiary> getAllBeneficiaries(int userId){
		return benRepo.getAllBeneficiaries(userId);
	}
}
