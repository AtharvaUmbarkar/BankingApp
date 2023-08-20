package com.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.models.Beneficiary;
import com.bankingapp.service.BeneficiaryService;

@RestController
@CrossOrigin("http://localhost:3000")
public class BeneficiaryController {
	@Autowired
	BeneficiaryService benService;
	@PostMapping("/save/beneficiary")
	public ResponseEntity<Object> saveBeneficiary(@RequestBody Beneficiary ben, @RequestParam("userName") String userName) {
//		System.out.println("HERE: " + ben.getName());
		Beneficiary beneficiary =  benService.saveBeneficiary(ben, userName);
		boolean succes;
		if(beneficiary == null) {
			return new ResponseEntity<>("failed to create beneficiary",HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(beneficiary, HttpStatus.OK);
		}
	}
	
	@GetMapping("/getAllBeneficiaries")
	public List<Beneficiary> getAllBeneficiary(@RequestParam String userName){
		return benService.getAllBeneficiaries(userName);
	}
}
