package com.daisy.happyhorse.model.services.factory;

import com.daisy.happyhorse.model.services.IService;
import com.daisy.happyhorse.model.services.exception.ServiceLoadException;
import com.daisy.happyhorse.model.services.manager.PropertyManager;

//reflection gives us information about the class that an object belongs to in addition to the methods that class can be executed
import java.lang.reflect.Constructor;
//PropertyManager's only job is to have access to PropertyManager files
public class ServiceFactory implements IServiceFactory {
	PropertyManager propManager;
	
	public ServiceFactory(PropertyManager propManager) {
		this.propManager = propManager;
	}
	
	public IService getService(String service) throws ServiceLoadException {
		try {
			Class<?> c = Class.forName(getRuntimeImplementationName(service));
			Constructor<?> ctor = c.getConstructor();
			return (IService) ctor.newInstance();
		}
		catch (Exception e) {
			throw new ServiceLoadException("Error: Could not load " + service, e);
		}
	}
	
	private String getRuntimeImplementationName(String serviceName) {
		return propManager.getProperty(serviceName);
	}
}