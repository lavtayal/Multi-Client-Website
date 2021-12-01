package com.cg.opmtoolapi.exception;

public class EnquiryNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EnquiryNotFoundException(String args) {
		super(args);
	}
	public EnquiryNotFoundException() {
		super();
	}

}
