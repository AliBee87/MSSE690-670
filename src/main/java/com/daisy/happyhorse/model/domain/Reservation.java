package com.daisy.happyhorse.model.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Reservation implements Serializable {
	
	private static final long serialVersionUID = -8734216578954329051L;
	private Integer id;
	private Customer customer;
	private ArrayList<Horse> reservedHorses;
	
	public Reservation() {
		
	}
	
	public Reservation(Integer id, Customer customer, ArrayList<Horse> reservedHorses) {
		super();
		this.id = id;
		this.customer = customer;
		this.reservedHorses = reservedHorses;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public ArrayList<Horse> getReservedHorses() {
		return reservedHorses;
	}
	public void setReservedHorses(ArrayList<Horse> reservedHorses) {
		this.reservedHorses = reservedHorses;
	}
}