package com.bankingapp.exception;

public class NoDataFoundException extends Exception{
	private String message;
	
	public NoDataFoundException(String message){
		super(message);
//		this.message = message;
	}
}
