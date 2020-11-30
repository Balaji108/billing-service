package com.billing.domain;

import java.util.List;

public class Bill {

	private List<MenuItem> servedItems;
	private double serviceCharge;
	private double total;

	public List<MenuItem> getServedItems() {
		return servedItems;
	}

	public void setServedItems(List<MenuItem> servedItems) {
		this.servedItems = servedItems;
	}

	public double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
