package com.cg.opmtoolapi.exception;

public class CompanyAlreadyExistsExceptionResponse {
	private String companyAlreadyExists;

	public String getCompanyAlreadyExists() {
		return companyAlreadyExists;
	}

	public void setCompanyAlreadyExists(String companyAlreadyExists) {
		this.companyAlreadyExists = companyAlreadyExists;
	}

	public CompanyAlreadyExistsExceptionResponse(String companyAlreadyExists) {
		super();
		this.companyAlreadyExists = companyAlreadyExists;
	}

}
