package com.cg.opmtoolapi.exception;

public class WrongPasswordException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WrongPasswordException(String args) {
		super(args);
	}
	public WrongPasswordException() {
		super();
	}

}