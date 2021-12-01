package com.cg.opmtoolapi.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.opmtoolapi.domain.Company;
import com.cg.opmtoolapi.domain.Delievery;
import com.cg.opmtoolapi.domain.Enquiry;
import com.cg.opmtoolapi.domain.FileDB;
import com.cg.opmtoolapi.domain.UserLogin;
import com.cg.opmtoolapi.domain.UserRegistration;
import com.cg.opmtoolapi.exception.CompanyAlreadyExistsException;
import com.cg.opmtoolapi.exception.CompanyNotFoundException;
import com.cg.opmtoolapi.exception.EnquiryNotFoundException;

import com.cg.opmtoolapi.exception.UserNotFoundException;
import com.cg.opmtoolapi.repository.CompanyRepository;
import com.cg.opmtoolapi.repository.DelieveryRepository;

import com.cg.opmtoolapi.service.CompanyService;
import com.cg.opmtoolapi.service.FileStorageService;
import com.cg.opmtoolapi.service.UserLoginService;
import com.cg.opmtoolapi.supply.ConstantSupply;
@Transactional
@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private DelieveryRepository delieveryRepository;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private UserRegistrationServiceImpl userRegistrationImpl;
	
	@Autowired
	private UserLoginService userLoginService;
	
	Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Override
	public Company createCompany(Company company) {
		Company checkCompany = getCompanyByCompanyCode(company.getCompanyCode().toLowerCase());
		if(checkCompany!=null)
			throw new CompanyAlreadyExistsException("Company already exists for the same code");
		company.setCompanyCode(company.getCompanyCode().toLowerCase());
		company.setVacancies(ConstantSupply.VACANCY);
		company.setServiceCharge(ConstantSupply.SERVICECHARGE);
		UserLogin user = new UserLogin();
		user.setUserName(company.getCompanyName());
		user.setEmail(company.getCompanyEmail());
		user.setPassword(company.getPassword());
		userLoginService.addEmployee(user);
		return companyRepository.save(company);
	}

	@Override
	public Iterable<Enquiry> getEnquiryByCompanyCode(String code) {
		Company company = getCompanyByCompanyCode(code.toLowerCase());
		logger.info("company data is:" + company);
		System.out.println(company);
		return company.getEnquiry();
	}

	@Override
	public Company getCompanyByCompanyCode(String companyName) {
		List<Company> allCompanies = companyRepository.findAll();
		Company company = null;
		for (Company each_company : allCompanies) {
			if (each_company.getCompanyName().equals(companyName)) {
				company = each_company;
			}
		}
		return company;
	}

	@Override
	public Delievery createDelievery(Enquiry enquiry) {
		UserRegistration userRegistration= userRegistrationImpl.getUserByEmail(enquiry.getEnquiryid());
		Delievery delievery = new Delievery();
		LocalDate currentdDate1 = LocalDate.now();
		LocalDate currentDatePlus1 = currentdDate1.plusDays(15);
		delievery.setDeliveryContact(enquiry.getEnquiryid());
		delievery.setDeliveryNumber(userRegistration.getMobileNo());
		delievery.setDeliveryStatus(ConstantSupply.ON_WAY);
		delievery.setDeliveryDate(currentDatePlus1);
		delievery.setRemark("It will reach to you soon");
		return delievery;
	}

	public void updateDelievery(String code, String id) {
		Company company = getCompanyByCompanyCode(code);
		if (company == null) {
			throw new CompanyNotFoundException("No company found");
		}
		Delievery delievery = delieveryRepository.findByDeliveryContact(id);
		delievery.setDeliveryStatus(ConstantSupply.REACHED);
		delievery.setRemark("Thanks for joining with us");
		delieveryRepository.save(delievery);
	}

	@Override
	public FileDB downloadFile(String code,String userId) {
		Iterable<Enquiry> enquiries = getEnquiryByCompanyCode(code);
		Enquiry enquiry = null;
		for(Enquiry each_enquiry:enquiries) {
			if(each_enquiry.getEnquiryid().equals(userId)) {
				enquiry = each_enquiry;	
			}
		}
		if(enquiry == null)
			throw new EnquiryNotFoundException("No enquiry found for this id:");
		UserRegistration userRegistration = userRegistrationImpl.getUserByEmail(enquiry.getEnquiryid());
		return fileStorageService.getFile(userRegistration.getFileDB().getId());
	}

	@Override
	public void updateEnquiryInCompany(String code,Company entity) {
		Company company = getCompanyByCompanyCode(code);
		if(company==null)
			throw new CompanyNotFoundException("User not found for this name");
		if(entity.getEnquiry()!=null)
			company.setEnquiry(entity.getEnquiry());
		companyRepository.save(company);	
	}

	@Override
	public List<Enquiry> getUserByResponse(String response,String code) throws ParseException {
		List<UserRegistration> userRegistration = userRegistrationImpl.getAllUser();
		Company company = getCompanyByCompanyCode(code);
		if(company==null)
			throw new CompanyNotFoundException("company not found for this name");
		List<Enquiry> enquiry = company.getEnquiry();
		if(enquiry==null) {
			throw new EnquiryNotFoundException("No Enquiry Found for this company");
		}
		List<Enquiry> returnEnquiry = new ArrayList<>();
		if(response.contains("-")) {
			final String pattern = "yyyy-MM-dd";
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			final Date searchDate = simpleDateFormat.parse(response);
			for(Enquiry each_enquiry:enquiry) {
				if(each_enquiry.getEnquirydate().equals(searchDate))
					returnEnquiry.add(each_enquiry);	
			}
		}
		else {
			for(int i=0;i<userRegistration.size();i++) {
				List<Enquiry> userEnquiry = userRegistration.get(i).getEnquiry();
				for(Enquiry each_enquiry:userEnquiry) {
					if(each_enquiry.getCompany().getCompanyCode().equals(code)&&userRegistration.get(i).getFirstName().equals(response)) {
						returnEnquiry.add(each_enquiry);
					}
				}
			}
			
		}
		return returnEnquiry;
	}

	@Override
	public UserRegistration getUserByPassport(String code, long passportno) {
		UserRegistration userRegistration = null;
		List<UserRegistration> allUsers = userRegistrationImpl.getAllUser();
		if(allUsers==null)
			throw new UserNotFoundException("No user found");
		for(UserRegistration user:allUsers) {
			if(user.getPassportNo()!=null) {
				if(user.getCompany().getCompanyCode().equals(code)&&user.getPassportNo().equals(passportno)) {
					userRegistration = user;
				}
			}
			
		}
		return userRegistration;
	}

	@Override
	public void updateCompany(Company company) {
		Company updateCompany = getCompanyByCompanyCode(company.getCompanyName());
		if(company.getVacancies()!=null)
			updateCompany.setVacancies(company.getVacancies()-1);
		else {
			company.setVacancies(ConstantSupply.VACANCY);
		}
		
		companyRepository.save(updateCompany);
	}


}
