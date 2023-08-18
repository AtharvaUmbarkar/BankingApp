package com.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Beneficiary saveBeneficiary(@RequestBody Beneficiary ben) {
		System.out.println("HERE: " + ben.getName());
		return benService.saveBeneficiary(ben);
	}
	
	@GetMapping("/getAllBeneficiaries")
	public List<Beneficiary> getAllBeneficiary(@RequestParam int userId){
		return benService.getAllBeneficiaries(userId);
	}
}
