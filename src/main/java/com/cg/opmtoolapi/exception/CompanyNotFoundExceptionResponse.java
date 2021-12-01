package com.cg.opmtoolapi.exception;

public class CompanyNotFoundExceptionResponse {
	private String companyNotFound;

	public String getCompanyNotFound() {
		return companyNotFound;
	}

	public void setCompanyNotFound(String companyNotFound) {
		this.companyNotFound = companyNotFound;
	}

	public CompanyNotFoundExceptionResponse(String companyNotFound) {
		super();
		this.companyNotFound = companyNotFound;
	}
}
