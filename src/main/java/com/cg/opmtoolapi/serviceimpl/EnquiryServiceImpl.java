package com.cg.opmtoolapi.serviceimpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.opmtoolapi.domain.Company;
import com.cg.opmtoolapi.domain.Enquiry;
import com.cg.opmtoolapi.domain.UserRegistration;
import com.cg.opmtoolapi.exception.CompanyNotFoundException;
import com.cg.opmtoolapi.exception.UserNotFoundException;
import com.cg.opmtoolapi.repository.EnquiryRepository;
import com.cg.opmtoolapi.service.EnquiryService;
import com.cg.opmtoolapi.supply.ConstantSupply;

@Transactional
@Service
public class EnquiryServiceImpl implements EnquiryService {
	@Autowired
	private EnquiryRepository enqRepo;

	@Autowired
	private CompanyServiceImpl companyServiceImpl;

	@Autowired
	private UserRegistrationServiceImpl userRegistrationServiceImpl;

	@Override
	public String createEnquiry(String userName, String code) {
		UserRegistration userData = userRegistrationServiceImpl.getUserByUserName(userName);
		if (userData == null)
			throw new UserNotFoundException("User Not Found for this user name:");
		if (userData.getFileDB() == null)
			return "Please Attach the documents First";
		if (userData.getDelievery() != null)
			return "Thank you for showing interest but you have already recieved passport";
		Company company = companyServiceImpl.getCompanyByCompanyCode(code);
		if (company == null)
			throw new CompanyNotFoundException("Company not found");
		List<Enquiry> companyEnquiries = company.getEnquiry();
		for (int i = 0; i < companyEnquiries.size(); i++) {
			if (companyEnquiries.get(i).getEnquiryid().equals(userData.getEmail())) {
				return "already recieved enquiry";
			}
		}
		List<Enquiry> enquiryList = userData.getEnquiry();
		if (enquiryList != null) {
			for (int i = 0; i < enquiryList.size(); i++) {
				if (enquiryList.get(i).getCompany().getCompanyCode().equals(code)) {
					return "you have already applied to this company";
				}
			}
		}

		Enquiry enquiry = new Enquiry();
		enquiry.setEnquiryid(userData.getEmail());
		enquiry.setEnquirydate(new Date());
		enquiry.setEnquirystatus(ConstantSupply.PENDING);
		enquiry.setEnquirydesc("I would like to apply for passport through your company");
		enquiry.setCompany(company);
		enqRepo.save(enquiry);
		companyEnquiries.add(enquiry);
		company.setEnquiry(companyEnquiries);
		companyServiceImpl.updateEnquiryInCompany(code, company);
		enquiryList.add(enquiry);
		userData.setEnquiry(enquiryList);
		userRegistrationServiceImpl.update(userName, userData);
		return "Enquiry Sent";
	}

	@Override
	public List<Enquiry> getAllEnquiry() {
		return enqRepo.findAll();
	}

	@Override
	public List<Enquiry> getEnquiryById(String enquiryid) {
		return enqRepo.findByEnquiryid(enquiryid);
	}

	@Override
	public Enquiry updateEnquiry(Enquiry enquiry) {
		List<Enquiry> updateEnquiry = enqRepo.findByEnquiryid(enquiry.getEnquiryid());
		Enquiry updatedEnquiry = null;
		for (Enquiry each_enquiry : updateEnquiry) {
			if (each_enquiry.getEnquiryid().equals(enquiry.getEnquiryid()))
				updatedEnquiry = each_enquiry;
		}
		updatedEnquiry.setCompany(enquiry.getCompany());
		updatedEnquiry.setEnquirydate(enquiry.getEnquirydate());
		updatedEnquiry.setEnquirydesc("your query has been responded ");
		updatedEnquiry.setEnquiryid(enquiry.getEnquiryid());
		updatedEnquiry.setEnquirystatus(enquiry.getEnquirystatus());
		UserRegistration user = userRegistrationServiceImpl.getUserByEmail(enquiry.getEnquiryid());
		userRegistrationServiceImpl.update(user.getUserName(), user);
		return enqRepo.save(updatedEnquiry);
	}

	@Override
	public void deleteEnquiry(Enquiry enquiry) {
		enqRepo.delete(enquiry);
	}

}