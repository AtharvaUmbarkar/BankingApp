package com.bankingapp.exception;

public class ErrorResponse {
	private int status;
	private String message;
	
	public ErrorResponse(String message) {
//		super();
		this.message = message;
	}
	
	public ErrorResponse(int statusCode, String message) {
		super();
		this.status = statusCode;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int statuscode) {
		status = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
