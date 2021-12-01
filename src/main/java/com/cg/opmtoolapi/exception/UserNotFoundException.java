package com.cg.opmtoolapi.exception;

public class UserNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String args) {
		super(args);
	}
	public UserNotFoundException() {
		super();
	}

}
