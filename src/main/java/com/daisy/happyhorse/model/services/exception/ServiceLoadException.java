package com.daisy.happyhorse.model.services.exception;

public class ServiceLoadException extends Exception {
	
	private static final long serialVersionUID = 8627096371982638751L;
	
	public ServiceLoadException(final String inMessage, final Throwable inNestedException) {
		super(inMessage, inNestedException);
	}
	
	
}