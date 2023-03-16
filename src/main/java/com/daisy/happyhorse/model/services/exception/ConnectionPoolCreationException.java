package com.daisy.happyhorse.model.services.exception;

public class ConnectionPoolCreationException extends Exception {

	private static final long serialVersionUID = -2965760782717030285L;
	
	public ConnectionPoolCreationException() {
		super();
	}
	public ConnectionPoolCreationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public ConnectionPoolCreationException(String message, Throwable cause) {
		super(message, cause);
	}
	public ConnectionPoolCreationException(String message) {
		super(message);
	}
	public ConnectionPoolCreationException(Throwable cause) {
		super(cause);
	}

}
