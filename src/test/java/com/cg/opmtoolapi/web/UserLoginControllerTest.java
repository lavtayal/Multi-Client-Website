package com.cg.opmtoolapi.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.opmtoolapi.domain.UserLogin;
import com.cg.opmtoolapi.repository.CompanyRepository;
import com.cg.opmtoolapi.repository.QuizzRepository;
import com.cg.opmtoolapi.repository.UserLoginRepository;
import com.cg.opmtoolapi.repository.UserRepository;
import com.cg.opmtoolapi.serviceimpl.UserLoginServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserLoginControllerTest {

	@Autowired
	private UserLoginServiceImpl userServiceImpl;
	
	@MockBean
	private UserLoginRepository userLoginRepository;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private CompanyRepository companyRepo;
	
	@MockBean
	private QuizzRepository quizzRepo;

//	@Test
//	void addUser() {
//	UserLogin user = new UserLogin(121L,"anuj","anujjadon050@gmail.com","Anuj@1234");
//	when(userLoginRepository.save(user)).thenReturn(user);
//	assertEquals(user, userServiceImpl.addUser(user));
//	}
	
}