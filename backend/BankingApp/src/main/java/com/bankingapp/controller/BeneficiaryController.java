package com.bankingapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.BeneficiaryDTO;
import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.models.Beneficiary;
import com.bankingapp.service.BeneficiaryService;
import com.bankingapp.types.AddBeneficiaryModel;

@RestController
@CrossOrigin("http://localhost:3000")
public class BeneficiaryController {
	@Autowired
	BeneficiaryService benService;
	@Autowired
	ModelMapper modelMapper;
	@PostMapping("/save/beneficiary")
	public boolean saveBeneficiary(@RequestBody AddBeneficiaryModel benModel) throws ResourceNotFoundException, AlreadyExistsException {
		benService.saveBeneficiary(benModel);
		return true;
	}
	
	@GetMapping("/getAllBeneficiaries")
	public List<BeneficiaryDTO> getAllBeneficiary(@RequestParam String userName) throws ResourceNotFoundException, NoDataFoundException{
		return benService.getAllBeneficiaries(userName).stream().map(ben -> modelMapper.map(ben, BeneficiaryDTO.class)).collect(Collectors.toList());
	}
}
