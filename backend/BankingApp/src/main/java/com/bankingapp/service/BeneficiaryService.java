package com.bankingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.interfaces.BeneficiaryServiceInterface;
import com.bankingapp.models.Account;
import com.bankingapp.models.Beneficiary;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepo;
import com.bankingapp.repository.BeneficiaryRepo;
import com.bankingapp.repository.CustomerRepo;
import com.bankingapp.types.AddBeneficiaryModel;

@Service
public class BeneficiaryService implements BeneficiaryServiceInterface{
	@Autowired
	BeneficiaryRepo benRepo;
	@Autowired
	CustomerRepo custRepo;
	@Autowired
	AccountRepo acntRepo;
	public Beneficiary saveBeneficiary(AddBeneficiaryModel benModel, String userName) throws ResourceNotFoundException, AlreadyExistsException {
		Optional<Customer> custObj = custRepo.findByUserName(userName);
		Optional<Account> acntObj = acntRepo.findById(benModel.getAccountNumber());
		if(custObj.isPresent() && acntObj.isPresent()) { //also check if same user alter
			Customer cust = custObj.get();
			Account acnt = acntObj.get();
			if(cust.getBeneficiaries().stream().anyMatch(ben -> (ben.getAccount().getAccountNumber()==(benModel.getAccountNumber()))))
				throw new AlreadyExistsException("Beneficary with given account number already exist");
			Beneficiary ben = new Beneficiary();
			ben.setName(benModel.getName());
			ben.setNickname(benModel.getNickname());
			ben.setAccount(acnt);
			ben.setCustomer(cust);
			benRepo.save(ben);
			return ben;
		}
		else {
			throw new ResourceNotFoundException("customer or account does not exist");
		}
		
	}
	
	public List<Beneficiary> getAllBeneficiaries(String userName) throws ResourceNotFoundException{
		Optional<Customer> obj = custRepo.findByUserName(userName);
		if(obj.isPresent()) {
			return obj.get().getBeneficiaries();
		}
		else {
			throw new ResourceNotFoundException("Customer does not exist");
		}
		
	}
	
	public String deleteBeneficiary(int Id, String userName) throws ResourceNotFoundException, UnauthorizedAccessException {
		
		Beneficiary ben = benRepo.findById(Id).get();
		String result = "";
		if (ben == null) {
			throw new ResourceNotFoundException("Beneficiary not Present");
		}
		else if(!ben.getCustomer().getUserName().equals(userName)) {
			throw new UnauthorizedAccessException("Beneficiary doesn't belong to the customer");
		}
		else {
			benRepo.deleteBeneficiary(Id);
			return "Beneficiary with Id :" + Id + "deleted";
		}
		
	}
}
