package com.daisy.happyhorse.model.services.repository;

import com.daisy.happyhorse.model.domain.Horse;

public class RepositoryWrapper implements IRepositoryWrapper {
	public IRepository<Horse> horses;
	
	public RepositoryWrapper() {
		horses = new HorseRepository();
	}
	
	@Override
	public IRepository<Horse> Horses() {
		return horses;
	}
}