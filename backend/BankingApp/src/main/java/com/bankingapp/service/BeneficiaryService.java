package com.bankingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.models.Account;
import com.bankingapp.models.Beneficiary;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.BeneficiaryRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.types.AddBeneficiaryModel;

@Service
public class BeneficiaryService {
	@Autowired
	BeneficiaryRepo benRepo;
	@Autowired
	CustomerRepo custRepo;
	@Autowired
	AccountRepo acntRepo;
	public Beneficiary saveBeneficiary(Beneficiary ben, String userName) {
		Optional<Customer> obj = custRepo.findByUserName(userName);
		if(obj.isPresent() && acntRepo.findById(ben.getAccountNumber()).isPresent()) { //also check if same user alter
			ben.setCustomer(obj.get());
			return benRepo.save(ben);
		}
		else {
			return null;
		}
		
	}
	
	public List<Beneficiary> getAllBeneficiaries(String userName){
		Optional<Customer> obj = custRepo.findByUserName(userName);
		if(obj.isPresent()) {
			return obj.get().getBeneficiaries();
		}
		else {
			return List.of();
		}
		
	}
}
