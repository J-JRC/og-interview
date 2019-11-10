package com.exception;

public class UIException extends Exception {
	private static final long serialVersionUID = 689088837052178789L;

	/**
	 * Constructs a UIException Object via message
	 * 
	 * @param message:String
	 */
	public UIException(String message) {
		super(message);
	}

	/**
	 * Constructs a UIException Object via message and cause
	 * 
	 * @param message:String
	 * @param cause:Throwable
	 */
	public UIException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a UIException Object via cause
	 * 
	 * @param cause:Exception
	 */
	public UIException(Exception cause) {
		super(cause);
	}

}
