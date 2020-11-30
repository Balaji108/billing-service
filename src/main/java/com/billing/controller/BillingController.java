package com.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.domain.Bill;
import com.billing.domain.MenuItem;
import com.billing.service.BillingService;

@RestController
@RequestMapping("/billings")
public class BillingController {
	
	@Autowired
	private BillingService billingService;
	
	@PostMapping("/generate")
	public Bill generateBill(@RequestBody List<MenuItem> items) {
		return billingService.generateBilling(items);
	}

}
