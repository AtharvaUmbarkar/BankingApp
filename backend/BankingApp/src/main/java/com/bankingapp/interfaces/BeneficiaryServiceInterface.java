package com.bankingapp.interfaces;

import java.util.List;
import java.util.Optional;

import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Beneficiary;
import com.bankingapp.models.Customer;
import com.bankingapp.types.AddBeneficiaryModel;

public interface BeneficiaryServiceInterface {
	public Beneficiary saveBeneficiary(AddBeneficiaryModel benModel, String userName) throws ResourceNotFoundException, AlreadyExistsException;
	public List<Beneficiary> getAllBeneficiaries(String userName) throws ResourceNotFoundException;
	public String deleteBeneficiary(int Id, String userName) throws ResourceNotFoundException, UnauthorizedAccessException;
}
