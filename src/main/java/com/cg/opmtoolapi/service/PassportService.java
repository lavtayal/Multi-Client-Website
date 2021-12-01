package com.cg.opmtoolapi.service;


import com.cg.opmtoolapi.domain.Passport;
import com.cg.opmtoolapi.domain.UserRegistration;

public interface PassportService {
	public String addPassport(UserRegistration userRegistration);
	public void updatePassport(Passport passport);
	public Passport getPassportById(String mail);
	public Iterable<Passport> getAllPassport();

}
