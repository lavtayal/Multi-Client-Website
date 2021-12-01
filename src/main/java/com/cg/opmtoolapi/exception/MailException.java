package com.cg.opmtoolapi.exception;

public class MailException  extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MailException(String args) {
		super(args);
	}
	public MailException() {
		super();
	}


}
