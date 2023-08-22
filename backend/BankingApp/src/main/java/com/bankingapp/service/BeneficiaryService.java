package com.bankingapp.service;

import java.util.List;
import java.util.Optional;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
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
	public Beneficiary saveBeneficiary(AddBeneficiaryModel benModel) throws ResourceNotFoundException, AlreadyExistsException {
		Optional<Customer> custObj = custRepo.findByUserName(benModel.getUserName());
		Optional<Account> acntObj = acntRepo.findById(benModel.getAccountNumber());
		if(custObj.isPresent() && acntObj.isPresent()) { //also check if same user alter
			Customer cust = custObj.get();
			if(cust.getBeneficiaries().stream().anyMatch(ben -> (ben.getAccount().getAccountNumber()==(benModel.getAccountNumber()))))
				throw new AlreadyExistsException("Beneficary with given account number already exist");
			Beneficiary ben = new Beneficiary();
			ben.setName(benModel.getName());
			ben.setNickname(benModel.getNickname());
			ben.setAccount(acntObj.get());
			ben.setCustomer(custObj.get());
			benRepo.save(ben);
			return ben;
		}
		else {
			throw new ResourceNotFoundException("customer or account does not exist");
		}
		
	}
	
	public List<Beneficiary> getAllBeneficiaries(String userName) throws ResourceNotFoundException, NoDataFoundException{
		Optional<Customer> obj = custRepo.findByUserName(userName);
		if(obj.isPresent()) {
			List<Beneficiary> list = obj.get().getBeneficiaries();
			if(list.isEmpty())
				throw new NoDataFoundException("Beneficary list is empty");
			else
				return list;
		}
		else {
			throw new ResourceNotFoundException("Customer does not exist");
		}
		
	}
}
