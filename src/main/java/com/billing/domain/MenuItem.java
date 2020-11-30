package com.billing.domain;

public class MenuItem {

	private String name;
	private String ingrediants; // this can be an list of items
	private Category category;
	private double price;
	
	public MenuItem(String name, String ingrediants, Category category, double price) {
		super();
		this.name = name;
		this.ingrediants = ingrediants;
		this.category = category;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngrediants() {
		return ingrediants;
	}

	public void setIngrediants(String ingrediants) {
		this.ingrediants = ingrediants;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
