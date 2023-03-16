package com.daisy.happyhorse.model.services.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daisy.happyhorse.model.domain.Horse;

public class JDBCDatabase implements IDatabase {

	@Override
	public List<Horse> getAllHorses() {
		ResultSet result = null;
		PreparedStatement stmt = null;
		Connection connection = null;
		ArrayList<Horse> horses = new ArrayList<>();
		
		try { 
			connection = ConnectionManager.getConnection();
			if (connection != null) {
				stmt = connection.prepareStatement("select * from horses");
				result = stmt.executeQuery();
				while (result.next() ) {
					horses.add(new Horse());
				}
			}
		} 
		catch(SQLException e ) {
			
		} finally {
			
		}
		return horses;
	}

	@Override
	public Horse getHorseById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
