package com.cg.opmtoolapi.exception;

public class UserCredentialFoundExceptionResponse {

	private String userCredFound;

	public UserCredentialFoundExceptionResponse(String userCredFound) {
		super();
		this.userCredFound = userCredFound;
	}

	public String getUserCredFound() {
		return userCredFound;
	}

	public void setUserCredFound(String userCredFound) {
		this.userCredFound = userCredFound;
	}
}
