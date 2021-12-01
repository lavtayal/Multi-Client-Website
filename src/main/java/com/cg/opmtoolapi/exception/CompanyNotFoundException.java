package com.cg.opmtoolapi.exception;

public class CompanyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException(String args) {
		super(args);
	}

	public CompanyNotFoundException() {
		super();
	}

}
