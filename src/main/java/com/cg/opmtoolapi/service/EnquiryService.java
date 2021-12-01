package com.cg.opmtoolapi.service;

import java.util.List;

import com.cg.opmtoolapi.domain.Enquiry;

public interface EnquiryService {
	public String createEnquiry(String mail,String code);
	public List<Enquiry> getAllEnquiry();
	public List<Enquiry> getEnquiryById(String enquiryid);
	public Enquiry updateEnquiry(Enquiry enquiry);
	public void deleteEnquiry(Enquiry enquiry);

}