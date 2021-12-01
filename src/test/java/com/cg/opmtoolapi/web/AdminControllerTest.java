package com.cg.opmtoolapi.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.cg.opmtoolapi.domain.Enquiry;
import com.cg.opmtoolapi.domain.UserLogin;
import com.cg.opmtoolapi.repository.CompanyRepository;
import com.cg.opmtoolapi.repository.EnquiryRepository;
import com.cg.opmtoolapi.repository.QuizzRepository;
import com.cg.opmtoolapi.repository.UserLoginRepository;
import com.cg.opmtoolapi.repository.UserRepository;
import com.cg.opmtoolapi.serviceimpl.EnquiryServiceImpl;
import com.cg.opmtoolapi.serviceimpl.UserLoginServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {

	@MockBean
	private UserLoginRepository userLoginRepository;

	@Autowired
	private EnquiryServiceImpl enquiryServiceImpl;
	
	@Autowired
	private UserLoginServiceImpl userServiceImpl;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private CompanyRepository companyRepo;

	@MockBean
	private QuizzRepository quizzRepo;

	@MockBean
	private EnquiryRepository enquiryRepo;

	@Test
	public void getAllEnquiry() throws ParseException {
		final String pattern = "yyyy-MM-dd";
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		final Date searchDate = simpleDateFormat.parse("2018-05-22");
		when(enquiryRepo.findAll())
				.thenReturn(Stream.of(new Enquiry("anujjadon050@gmail.com", searchDate, "PENDING", "please do"))
						.collect(Collectors.toList()));
		assertEquals(1, enquiryServiceImpl.getAllEnquiry().size());
	}
	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void getLoginById() {
//		String search = "anuj";
//		((Stream<UserLogin>) when(userLoginRepository.findAll()).thenReturn((List<UserLogin>) Stream.of(new UserLogin(121L,"anuj","anujjadon050@gmail.com","Anuj@1234")))).collect(Collectors.toList());
//		assertEquals(1,userServiceImpl.getLoginByUserName(search));
//	}

}
