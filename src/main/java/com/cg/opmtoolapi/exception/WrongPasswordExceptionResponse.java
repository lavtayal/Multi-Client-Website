package com.cg.opmtoolapi.exception;

public class WrongPasswordExceptionResponse {
	private String wrongPassword;

	public WrongPasswordExceptionResponse(String wrongPassword) {
		super();
		this.wrongPassword = wrongPassword;
	}

	public String geWrongPassword() {
		return wrongPassword;
	}

	public void setWrongPassword(String wrongPassword) {
		this.wrongPassword = wrongPassword;
	}

}