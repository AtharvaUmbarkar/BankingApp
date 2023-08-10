package com.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		return benService.saveBeneficiary(ben);
	}
}
