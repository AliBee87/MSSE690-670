package com.daisy.happyhorse.model.services.exception;

public class PropertyFileNotFoundException extends Exception {
	
	private static final long serialVersionUID = 9172635487656423191L;
	
	public PropertyFileNotFoundException(final String inMessage, final Throwable inNestedException) {
		super(inMessage, inNestedException);
	}
}