package com.cg.opmtoolapi.exception;

public class UserNotFoundExceptionResponse {
	private String userNotFound;

	public UserNotFoundExceptionResponse(String userNotFound) {
		super();
		this.userNotFound = userNotFound;
	}

	public String getUserNotFound() {
		return userNotFound;
	}

	public void setUserNotFound(String userNotFound) {
		this.userNotFound = userNotFound;
	}

}
