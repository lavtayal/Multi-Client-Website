package com.cg.opmtoolapi.service;

import java.text.ParseException;
import java.util.List;

import com.cg.opmtoolapi.domain.Company;
import com.cg.opmtoolapi.domain.Delievery;
import com.cg.opmtoolapi.domain.Enquiry;
import com.cg.opmtoolapi.domain.FileDB;
import com.cg.opmtoolapi.domain.UserRegistration;

public interface CompanyService {
	public Company createCompany(Company company);
	public Iterable<Enquiry> getEnquiryByCompanyCode(String code);
	public void updateCompany(Company company);
	public Company getCompanyByCompanyCode(String code);
	public Delievery createDelievery(Enquiry enquiry); 
	public FileDB downloadFile(String code,String userId);
	public void updateEnquiryInCompany(String code,Company company);
	public List<Enquiry> getUserByResponse(String response,String code)throws ParseException;
	public UserRegistration getUserByPassport(String code,long passportno);
	
}
