package com.daisy.happyhorse.model.services.repository;

import com.daisy.happyhorse.model.services.exception.ConnectionPoolCreationException;
import com.daisy.happyhorse.model.services.manager.PropertyManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import java.beans.PropertyVetoException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionManager {
	static Logger log = LogManager.getLogger(ConnectionManager.class);

	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	

	public ConnectionManager(PropertyManager propManager) throws ConnectionPoolCreationException {
		System.out.println("-Creating Connection Pool");

		boolean isCreatePool = true;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			cpds = new ComboPooledDataSource();

			// set Driver
			String driver = propManager.getProperty("jdbc.driver");
			if (driver != null) {
				cpds.setDriverClass(driver); // loads driver
			} else {
				stringBuilder.append("jdbc.driver not set \n");
				isCreatePool = false;
			}

			// set url
			String url = propManager.getProperty("jdbc.url");
			if (url != null) {
				cpds.setJdbcUrl(url);
			} else {
				stringBuilder.append("jdbc.url not set \n");
				isCreatePool = false;
			}

			// set user
			String user = propManager.getProperty("jdbc.user");
			if (user != null) {
				cpds.setUser(user);
			} else {
				stringBuilder.append("jdbc.user not set \n");
				isCreatePool = false;
			}

			// set password
			String password = propManager.getProperty("jdbc.password");
			if (password != null) {
				cpds.setPassword(password);
			} else {
				stringBuilder.append("jdbc.password not set \n");
				isCreatePool = false;
			}

			// set jdbcMinPoolSize
			String jdbcMinPoolSize = propManager.getProperty("jdbc.min.pool.size");
			if (jdbcMinPoolSize != null) {
				cpds.setMaxPoolSize(Integer.parseInt(jdbcMinPoolSize));
			} else {
				stringBuilder.append("jdbc.min.pool.size not set \n");
				isCreatePool = false;
			}

			// set jdbcMaxPoolSize
			String jdbcMaxPoolSize = propManager.getProperty("jdbc.max.pool.size");
			if (jdbcMaxPoolSize != null) {
				cpds.setMinPoolSize(Integer.parseInt(jdbcMaxPoolSize));
			} else {
				stringBuilder.append("jdbc.max.pool.size not set \n");
				isCreatePool = false;
			}
		} catch (PropertyVetoException pve) {
			// replace with log4 logging
			// log.error (sqe.getClass()+": "+ sqe.getMessage(), sqe);
			System.out.println(pve.getClass() + ": " + pve.getMessage());
			StringWriter errors;
			pve.printStackTrace(new PrintWriter(errors = new StringWriter()));
			System.out.println(errors.toString());

			throw new ConnectionPoolCreationException("Unable to create connection pool", pve);
		}

		if (!isCreatePool) {
			throw new ConnectionPoolCreationException(
					"Unable to create connection pool due to: \n" + stringBuilder.toString());
		}
		System.out.println("-Connection Pool creation completed");
	}

	/**
	 * Retrieves a connection from the connection pool
	 * 
	 * @return Connection if available or returns null
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = cpds.getConnection();
		} catch (SQLException e) {
			log.error("Unable to perform sql query.", e);
		} 
		return connection;
	}

	
	public void shutDown() {
		try {
			if (cpds != null) {
				System.out.println("Destroying datasource");
				DataSources.destroy(cpds);
			} // release datasource
		} catch (SQLException e) {
			// replace with log4 logging
			// log.error (sqe.getClass()+": "+ sqe.getMessage(), sqe);
			System.out.println(e.getClass() + ": " + e.getMessage());
			StringWriter errors;
			e.printStackTrace(new PrintWriter(errors = new StringWriter()));
			System.out.println(errors.toString());
		}
	} // end shutDown



}
