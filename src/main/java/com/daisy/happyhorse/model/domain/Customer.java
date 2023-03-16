package com.daisy.happyhorse.model.domain;

import java.io.Serializable;

public class Customer implements Serializable {
	
	private static final long serialVersionUID = -687991492884005033L;
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	
	public Customer() {
		
	}
	
	public Customer(Integer id, String firstName, String lastName, String email, String phone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone; 
	}
}