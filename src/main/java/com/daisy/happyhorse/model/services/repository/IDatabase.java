package com.daisy.happyhorse.model.services.repository;

import java.util.List;

import com.daisy.happyhorse.model.domain.Horse;

public interface IDatabase {
	
	List<Horse> getAllHorses();
	Horse getHorseById(int Id);

}
