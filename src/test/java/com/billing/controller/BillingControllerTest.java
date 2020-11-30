package com.billing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.billing.domain.Bill;
import com.billing.domain.Category;
import com.billing.domain.MenuItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BillingControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private static List<MenuItem> onlyDrinks;
	private static List<MenuItem> foodAndDrink;
	
	@BeforeAll
	public static void setUp() {
		onlyDrinks =  new LinkedList<>();
		onlyDrinks.add(new MenuItem("Cola - Cold", "", Category.DRINK, .5));
		onlyDrinks.add(new MenuItem("Coffee - Hot ", "", Category.DRINK, 1));
		
		foodAndDrink =  new LinkedList<>();
		foodAndDrink.add(new MenuItem("Cheese Sandwich - Cold", "", Category.FOOD, 2));
		foodAndDrink.add(new MenuItem("Steak Sandwich - Hot", "", Category.FOOD, 4.5));
		foodAndDrink.addAll(onlyDrinks);
	}
	
	@Test
	void testGenerateBill_onlyDrinks() throws JsonProcessingException {
		String url = "http://localhost:" + port + "/billings/generate";
		ResponseEntity<Bill> bill = restTemplate.postForEntity(url, onlyDrinks, Bill.class); 
		assertNotNull(bill, "Generated ResponseEntity cannot be null");
		assertEquals(HttpStatus.OK, bill.getStatusCode());
		assertNotNull(bill.getBody(), "Generated bill cannot be null");
		assertEquals(1.5, bill.getBody().getTotal());
		
	}
	
	@Test
	void testGenerateBill_withFood() {
		String url = "http://localhost:" + port + "/billings/generate";
		ResponseEntity<Bill> bill = restTemplate.postForEntity(url, foodAndDrink, Bill.class); 
		assertNotNull(bill, "Generated ResponseEntity cannot be null");
		assertEquals(HttpStatus.OK, bill.getStatusCode());
		assertNotNull(bill.getBody(), "Generated bill cannot be null");
		assertEquals(8.8, bill.getBody().getTotal());
	}

}
