package com.cg.opmtoolapi.exception;

public class CompanyAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CompanyAlreadyExistsException(String args)
	{
		super(args);
	}
	public CompanyAlreadyExistsException() {
		super();
	}
	

}
