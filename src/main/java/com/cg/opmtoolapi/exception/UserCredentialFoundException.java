package com.cg.opmtoolapi.exception;

public class UserCredentialFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserCredentialFoundException(String args) {
		super(args);
	}
	public UserCredentialFoundException()
	{
		super();
	}
}
