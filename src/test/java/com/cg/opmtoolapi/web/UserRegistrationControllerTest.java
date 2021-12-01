package com.cg.opmtoolapi.web;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
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
import com.cg.opmtoolapi.serviceimpl.UserRegistrationServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRegistrationControllerTest {
	@MockBean
	private UserLoginRepository userLoginRepository;
	
	@Autowired
	private UserRegistrationServiceImpl userRegistrationServiceImpl;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private CompanyRepository companyRepo;
	
	@MockBean
	private QuizzRepository quizzRepo;
	
	@Test
    public void getAllCompanies() {
	when(companyRepo.findAll()).thenReturn(Stream.of(new Company("2021","ksolves","info@ksolves.com","Anuj@1234","b","s","f")).collect(Collectors.toList()));
	assertEquals(1,userRegistrationServiceImpl.getAllCompanies().size());
}
	@Test
	public void getCompanyBy() {
		String search = "b";
		when(companyRepo.findAll()).thenReturn(Stream.of(new Company("2021","ksolves","info@ksolves.com","Anuj@1234","b","s","f")).collect(Collectors.toList()));
		assertEquals(1,userRegistrationServiceImpl.getCompaniesByResponse(search).size());
	}
}