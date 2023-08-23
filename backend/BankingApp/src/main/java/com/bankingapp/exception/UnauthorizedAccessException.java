package com.bankingapp.exception;

public class UnauthorizedAccessException extends Exception{
	private String message;
	
	public UnauthorizedAccessException(String message) {
		super(message);
	}

}
