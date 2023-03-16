package com.daisy.happyhorse.model.services.repository;

import com.daisy.happyhorse.model.domain.Horse;
import com.daisy.happyhorse.model.services.repository.ConnectionManager;

import java.util.ArrayList;

public class HorseRepository implements IRepository<Horse> {
    private IDatabase db;
    
    public HorseRepository() {
        db = new JDBCDatabase();
    }

    @Override
    public ArrayList<Horse> getAll() {
        return (ArrayList<Horse>) db.getAllHorses();
    }

    @Override
    public Horse getById(int id) {
        return null;
    }


    @Override
    public void Update(Horse entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void Delete(String id) {
        // TODO Auto-generated method stub

    }
}