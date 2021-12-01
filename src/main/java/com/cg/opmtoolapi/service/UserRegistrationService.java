package com.cg.opmtoolapi.service;

import java.util.List;

import com.cg.opmtoolapi.domain.Company;
import com.cg.opmtoolapi.domain.Enquiry;
import com.cg.opmtoolapi.domain.UserRegistration;

public interface UserRegistrationService {
	public String addUserRegistration(String userName,UserRegistration entity) ;

	public UserRegistration update(String userName,UserRegistration entity);

	public UserRegistration getUserByUserName(String name);

	public void deleteUser(String name);

	public List<UserRegistration> getAllUser();	
	
	public UserRegistration getUserByEmail(String name);

	public List<Company> getCompaniesByResponse(String response);

	public List<Company> getAllCompanies();
	
	public List<Enquiry> getEnquiryByUserName(String userName);

}
