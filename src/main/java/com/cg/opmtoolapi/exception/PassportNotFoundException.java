package com.cg.opmtoolapi.exception;

public class PassportNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PassportNotFoundException(String args) {
		super(args);
	}
	
	public PassportNotFoundException() {
		super();
	}

}
