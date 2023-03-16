package com.daisy.happyhorse.model.services.factory;

import com.daisy.happyhorse.model.services.IService;
import com.daisy.happyhorse.model.services.exception.ServiceLoadException;

public interface IServiceFactory {
	IService getService(String serviceName) throws ServiceLoadException;
}