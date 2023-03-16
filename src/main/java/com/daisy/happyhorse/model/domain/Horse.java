package com.daisy.happyhorse.model.domain;

import java.io.Serializable;

public class Horse implements Serializable {
	
	private static final long serialVersionUID= 1764538276548253411L;
	
	private Integer id;
	private float rentalRate;
	private String breed;
	private String specialty;
	private boolean isAvailable;
	
	public Horse() {
	}
	
	public Horse(Integer id, float rentalRate, String breed, String specialty, boolean isAvailable) {
		super();
		this.id = id;
		this.rentalRate = rentalRate;
		this.breed = breed;
		this.specialty = specialty;
		this.isAvailable = isAvailable;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public float getRentalRate() {
		return rentalRate;
	}
	public void setRentalRate(float rentalRate) {
		this.rentalRate = rentalRate;
	}
	public String getbreed() {
		return breed;		
	}
	public void setbreed(String breed) {
		this.breed = breed;
	}
	public String getspecialty() {
		return specialty;
	}
	public void setspecialty(String specialty) {
		this.specialty = specialty;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String toString() {
		return String.format("%d.\t%s\t%s: %f.2", id, breed, specialty, rentalRate, isAvailable);
	}
}