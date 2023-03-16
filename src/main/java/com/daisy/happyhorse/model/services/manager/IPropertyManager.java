package com.daisy.happyhorse.model.services.manager;

import com.daisy.happyhorse.model.services.exception.PropertyFileNotFoundException;

public interface IPropertyManager {
	void loadProperties(String propertyFileName) throws PropertyFileNotFoundException;
	
	String getProperty(String key);
}