package com.bankingapp.exception;

public class ErrorResponse {
	private int Statuscode;
	private String message;
	
	public ErrorResponse(String message) {
//		super();
		this.message = message;
	}
	
	public ErrorResponse(int statusCode, String message) {
		super();
		this.Statuscode = statusCode;
		this.message = message;
	}

	public int getStatuscode() {
		return Statuscode;
	}

	public void setStatuscode(int statuscode) {
		Statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
