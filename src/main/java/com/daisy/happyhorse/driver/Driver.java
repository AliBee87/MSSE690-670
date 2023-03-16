package com.daisy.happyhorse.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
	private static final Logger logger = LogManager.getLogger("com.daisy.happyhorse");
	
	public static void main(String[] args) {
		HappyApp app = new HappyApp();
		
		if (!app.Initialize()) {
			logger.error("Could not initialize system. Shutting down");
			System.exit(-1);
		}
		
		app.Run();
	}
}