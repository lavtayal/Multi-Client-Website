package com.cg.opmtoolapi.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.opmtoolapi.domain.Company;
import com.cg.opmtoolapi.repository.CompanyRepository;
import com.cg.opmtoolapi.repository.QuizzRepository;
import com.cg.opmtoolapi.repository.UserLoginRepository;
import com.cg.opmtoolapi.repository.UserRepository;
import com.cg.opmtoolapi.serviceimpl.CompanyServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class CompanyControllerTest {
	
	@MockBean
	private UserLoginRepository userLoginRepository;
	
	@Autowired
	private CompanyServiceImpl companyServiceImpl;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private CompanyRepository companyRepo;
	
	@MockBean
	private QuizzRepository quizzRepo;
	
	@Test
	void addCompany() {
		Company company = new Company();
		company.setCompanyCode("2021");
		company.setCompanyEmail("anujjadon050@gmail.com");
		company.setMinQualifiaction("a");
		company.setCompanyLocation("b");
		company.setPassportTitle("c");
		company.setCompanyName("anuj");
		when(companyRepo.save(company)).thenReturn(company);
		assertEquals(company,companyServiceImpl.createCompany(company));	
	}
}