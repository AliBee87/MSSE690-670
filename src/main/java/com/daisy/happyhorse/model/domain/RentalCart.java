package com.daisy.happyhorse.model.domain;

import java.util.ArrayList;

public class RentalCart {
	private Customer customer;
	private final ArrayList<Horse> selectedHorses;
	
	public RentalCart() {
		selectedHorses = new ArrayList<>();
	}
	
	public ArrayList<Horse> getHorses() {
		return selectedHorses;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}
}