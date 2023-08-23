package com.bankingapp.exception;

public class InvalidTypeException extends Exception{
	private String message;
	
	public InvalidTypeException (String message) {
		super(message);
	}

}
