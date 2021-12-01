package com.cg.opmtoolapi.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.opmtoolapi.domain.Passport;
import com.cg.opmtoolapi.domain.UserRegistration;
import com.cg.opmtoolapi.exception.PassportNotFoundException;
import com.cg.opmtoolapi.repository.PassportRepository;
import com.cg.opmtoolapi.service.PassportService;
import com.cg.opmtoolapi.supply.ConstantSupply;
@Transactional
@Service
public class PassportServiceImpl implements PassportService{
	@Autowired
	private PassportRepository passportRepository;
	@Autowired
	private UserRegistrationServiceImpl userServiceImpl;

	@Override
	public String addPassport(UserRegistration userRegistration) {
		if(userRegistration.getPassportNo()!=null) {
			return "your passport has already been generated";
		}
		List<Passport> allPassport = passportRepository.findAll();
		Passport passport = new Passport();
		passport.setPassportId(ConstantSupply.PASSPORTNO+allPassport.size());
		passport.setPassportDate(userRegistration.getDelievery().getDeliveryDate());
		passport.setPassportLocation(userRegistration.getCompany().getCompanyLocation());
		passport.setPassportMailId(userRegistration.getEmail());
		userRegistration.setPassportNo(ConstantSupply.PASSPORTNO+allPassport.size());
		userServiceImpl.update(userRegistration.getUserName(), userRegistration);
		passportRepository.save(passport);
		return "Created";
	}


	@Override
	public void updatePassport(Passport passport) {
		Passport updatePassport = passportRepository.findByPassportMailId(passport.getPassportMailId());
		if(!(passport.getPassportMailId().equals(updatePassport.getPassportMailId()))) {
			updatePassport.setPassportMailId(passport.getPassportMailId());
		}
		passportRepository.save(updatePassport);
	}

	@Override
	public Passport getPassportById(String mail) {
		Passport passport = passportRepository.findByPassportMailId(mail);
		if(passport==null) {
			throw new PassportNotFoundException("No Passport found for this mail");
		}
		return passport;
	}

	@Override
	public Iterable<Passport> getAllPassport() {
		return passportRepository.findAll();
	}



}
