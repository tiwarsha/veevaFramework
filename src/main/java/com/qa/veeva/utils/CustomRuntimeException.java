package com.qa.veeva.utils;

public class CustomRuntimeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomRuntimeException() {
		super();
	}

	public CustomRuntimeException(String message) {
		super(message);
	}

	public CustomRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomRuntimeException(Throwable cause) {
		super(cause);
	}
}
