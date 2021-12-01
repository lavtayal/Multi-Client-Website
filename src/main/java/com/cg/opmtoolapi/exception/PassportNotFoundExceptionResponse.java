package com.cg.opmtoolapi.exception;

public class PassportNotFoundExceptionResponse {
	String message;
	public PassportNotFoundExceptionResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
