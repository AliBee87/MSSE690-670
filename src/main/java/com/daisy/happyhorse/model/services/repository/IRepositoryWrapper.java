package com.daisy.happyhorse.model.services.repository;

import com.daisy.happyhorse.model.domain.Horse;

public interface IRepositoryWrapper {
	IRepository<Horse> Horses();
	
}