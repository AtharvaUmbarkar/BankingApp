package com.bankingapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.config.JwtTokenUtil;
import com.bankingapp.dto.BeneficiaryDTO;
import com.bankingapp.exception.AlreadyExistsException;
import com.bankingapp.exception.NoDataFoundException;
import com.bankingapp.exception.ResourceNotFoundException;
import com.bankingapp.exception.UnauthorizedAccessException;
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
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/save/beneficiary")
	public boolean saveBeneficiary(@RequestBody AddBeneficiaryModel benModel, @RequestHeader(value="Authorization", required=true) String bearerToken) throws ResourceNotFoundException, AlreadyExistsException {
		String userName = jwtTokenUtil.getUsernameFromToken(bearerToken.substring(7));
		benService.saveBeneficiary(benModel, userName);
		return true;
	}
	
	@GetMapping("/getAllBeneficiaries")
	public List<BeneficiaryDTO> getAllBeneficiary(@RequestHeader(value="Authorization", required=true) String bearerToken) throws ResourceNotFoundException{
		String userName = jwtTokenUtil.getUsernameFromToken(bearerToken.substring(7));
		return benService.getAllBeneficiaries(userName).stream().map(ben -> modelMapper.map(ben, BeneficiaryDTO.class)).collect(Collectors.toList());
	}
	
	//might need to verify if id belongs to the user
	@DeleteMapping("/delete/beneficiary")
	public String deleteBeneficiary(@RequestParam int Id,  @RequestHeader(value="Authorization", required=true) String bearerToken) throws ResourceNotFoundException, UnauthorizedAccessException {
		String userName = jwtTokenUtil.getUsernameFromToken(bearerToken.substring(7));
		return benService.deleteBeneficiary(Id, userName);
	}
	
}
