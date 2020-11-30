package com.billing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.billing.domain.Bill;
import com.billing.domain.Category;
import com.billing.domain.MenuItem;

@Service
public class BillingService {
	private Logger logger = LoggerFactory.getLogger(BillingService.class);
	
	@Value("${serviceCharge.percentage}")
	private double svcChargePercent;
	
	public Bill generateBilling(List<MenuItem> menuItems) {
		Bill bill = new Bill();
		bill.setServedItems(menuItems);
		double gross = menuItems.stream().collect(Collectors.summingDouble(MenuItem::getPrice));
		boolean hasFood = menuItems.stream().anyMatch(m -> Category.FOOD.equals(m.getCategory()));
		double serviceCharge = calculateServiceCharge(hasFood, gross);
		double total = gross +serviceCharge;
		bill.setServiceCharge(serviceCharge);
		bill.setTotal(total);
		logger.info("generated bill with Total bill amount: "+ total);
		return bill;
	}

	private double calculateServiceCharge(boolean hasFood, double total) {
		return hasFood? calculatePercentage(total) : 0;
	}
	
	private double calculatePercentage(double total) {
		return (total * svcChargePercent)/100;
		
	}
}
