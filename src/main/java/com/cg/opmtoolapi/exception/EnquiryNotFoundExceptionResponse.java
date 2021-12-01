package com.cg.opmtoolapi.exception;

public class EnquiryNotFoundExceptionResponse {
	private String enquiryNotFound;

	public String getEnquiryNotFound() {
		return enquiryNotFound;
	}

	public void setEnquiryNotFound(String enquiryNotFound) {
		this.enquiryNotFound = enquiryNotFound;
	}

	public EnquiryNotFoundExceptionResponse(String enquiryNotFound) {
		super();
		this.enquiryNotFound = enquiryNotFound;
	}

}
