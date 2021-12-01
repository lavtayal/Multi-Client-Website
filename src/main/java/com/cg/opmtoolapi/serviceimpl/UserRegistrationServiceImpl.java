package com.cg.opmtoolapi.serviceimpl;

import java.util.ArrayList;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.opmtoolapi.domain.Company;
import com.cg.opmtoolapi.domain.Enquiry;
import com.cg.opmtoolapi.domain.UserLogin;
import com.cg.opmtoolapi.domain.UserRegistration;
import com.cg.opmtoolapi.exception.UserNotFoundException;
import com.cg.opmtoolapi.repository.CompanyRepository;
import com.cg.opmtoolapi.repository.EnquiryRepository;
import com.cg.opmtoolapi.repository.UserLoginRepository;
import com.cg.opmtoolapi.repository.UserRepository;
import com.cg.opmtoolapi.service.UserLoginService;
import com.cg.opmtoolapi.service.UserRegistrationService;
@Transactional
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private UserLoginRepository userLoginRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private EnquiryRepository enqRepository;

	@Override
	public String addUserRegistration(String userName,UserRegistration entity) {
		UserLogin userLogin = userLoginRepository.findByUserName(userName);
		if(userLogin==null)
			throw new UserNotFoundException("No User Found for this mail Id");
		if(userLogin!=null&&userLogin.isCompleted()==true)
			return "Already has all the details of the user";
		entity.setUserName(userLogin.getUserName());
		entity.setEmail(userLogin.getEmail());
		userLogin.setCompleted(true);
		userLoginService.update(userName,userLogin);
		userRepository.save(entity);
		return "User's Info has been Successfull Stored";
	}

	@Override
	public UserRegistration update(String userName,UserRegistration entity){
		UserRegistration user = userRepository.findByUserName(userName);
		if(user==null)
			throw new UserNotFoundException("User not found for this name");
		if(entity.getMobileNo()!=null)
			user.setMobileNo(entity.getMobileNo());
		if(entity.getDelievery()!=null)
			user.setDelievery(entity.getDelievery());
		if(entity.getEnquiry()!=null)
			user.setEnquiry(entity.getEnquiry());
		if(entity.getCompany()!=null)
			user.setCompany(entity.getCompany());
		if(entity.getPassportNo()!=null)
			user.setPassportNo(entity.getPassportNo());
		if(entity.getQuizz()!=null)
			user.setQuizz(entity.getQuizz());
	 return	userRepository.save(user);
	}

	@Override
	public UserRegistration getUserByUserName(String name) {
		UserRegistration login = userRepository.findByUserName(name);
		if(login==null)
			throw new UserNotFoundException("User Not Found for this mail:");
		return login;
	}

	@Override
	public void deleteUser(String name) {
		UserRegistration data = userRepository.findByUserName(name);
		userRepository.delete(data);
	}

	@Override
	public List<UserRegistration> getAllUser() {
		return userRepository.findAll();
	}
	
	@Override
	public UserRegistration getUserByEmail(String mail) {
		UserRegistration login = userRepository.findByEmail(mail);
		if(login==null)
			throw new UserNotFoundException("User Not Found for this mail:");
		return login;
	}

	@Override
	public List<Company> getCompaniesByResponse(String response) {
		List<Company> returnCompanies = new ArrayList<>();
		List<Company> companies = companyRepository.findAll();
		for(Company each_company:companies) {
			if(each_company.getCompanyLocation().equals(response)) {
				returnCompanies.add(each_company);
			}
			if(each_company.getPassportTitle().equals(response)) {
				returnCompanies.add(each_company);
			}
			if(each_company.getMinQualifiaction().equals(response)) {
				returnCompanies.add(each_company);
			}
		}
		return returnCompanies;
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public List<Enquiry> getEnquiryByUserName(String userName) {
		
		List<Enquiry> sortedEnquiry = new ArrayList<>();
		UserLogin user = userLoginService.getLoginByUserName(userName);
		System.out.println(user);
		String userMail = user.getEmail();
		System.out.println(userMail);
		List<Enquiry> allEnquries =  enqRepository.findAll();
		for(Enquiry enquiry : allEnquries) {
			if (enquiry.getEnquiryid().equals(userMail)) {
				sortedEnquiry.add(enquiry);
			}
		}
		return sortedEnquiry;
	}



	
}
