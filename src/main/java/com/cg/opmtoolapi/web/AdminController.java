package com.cg.opmtoolapi.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.opmtoolapi.domain.Company;
import com.cg.opmtoolapi.domain.Enquiry;
import com.cg.opmtoolapi.domain.Quizz;
import com.cg.opmtoolapi.domain.UserLogin;
import com.cg.opmtoolapi.domain.UserRegistration;
import com.cg.opmtoolapi.exception.UserNotFoundException;
import com.cg.opmtoolapi.repository.CompanyRepository;
import com.cg.opmtoolapi.repository.QuizzRepository;
import com.cg.opmtoolapi.repository.UserLoginRepository;
import com.cg.opmtoolapi.repository.UserRepository;
import com.cg.opmtoolapi.service.UserLoginService;
import com.cg.opmtoolapi.service.UserRegistrationService;
import com.cg.opmtoolapi.serviceimpl.EnquiryServiceImpl;
import com.cg.opmtoolapi.serviceimpl.MapValidationErrorService;
import com.cg.opmtoolapi.serviceimpl.ReportServiceImpl;
import com.cg.opmtoolapi.serviceimpl.UserLoginServiceImpl;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/onlinepassport/admin")
public class AdminController {

	@Autowired
	private UserLoginService userLoginService;
	@Autowired
	private UserLoginRepository userLoginRepository;
	@Autowired
	private EnquiryServiceImpl enqService;
	@Autowired
	public UserRegistrationService userRegistrationService;
	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;

	@Autowired
	public MapValidationErrorService mapValidationErrorService;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	private QuizzRepository quizzRepository;

	final Logger log = org.slf4j.LoggerFactory.getLogger(AdminController.class);

	@GetMapping("/users/export/{code}/pdf")
	public Document exportToPDF(HttpServletResponse response, @PathVariable String code)
			throws DocumentException, IOException {
		List<UserRegistration> listUsers = userRegistrationService.getAllUser();
		List<Company> listCompanies = companyRepository.findAll();
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		
		Document document = null;
		ReportServiceImpl exporter = new ReportServiceImpl(listUsers, listCompanies);
		if (code.equals("1")) {
			String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
			response.setHeader(headerKey, headerValue);
			document = exporter.export(response, code);
		} else if (code.equals("2")) {
			String headerValue = "attachment; filename=company_" + currentDateTime + ".pdf";
			response.setHeader(headerKey, headerValue);
			document = exporter.export(response, code);
		} else {
			String headerValue = "attachment; filename=passport_" + currentDateTime + ".pdf";
			response.setHeader(headerKey, headerValue);
			document = exporter.export(response, code);
		}
		
		return document;
	}

	@GetMapping("/getuser/{name}")
	public ResponseEntity<UserLogin> getLoginById(@PathVariable(value = "name") String name) {
		UserLogin login = userLoginService.getLoginByUserName(name.toLowerCase());
		return ResponseEntity.ok().body(login);
	}

	@GetMapping("/listusersid")
	public List<UserLogin> getUserList() {
		return userLoginRepository.findAll();
	}

	@GetMapping("/enquiries")
	public List<Enquiry> getAllEnquiry() {
		return enqService.getAllEnquiry();
	}

	@GetMapping("/user/{name}")
	public ResponseEntity<UserRegistration> getRegisterById(@PathVariable(value = "name") String name) {

		UserRegistration userData = userRegistrationService.getUserByUserName(name.toLowerCase());
		if (userData == null)
			throw new UserNotFoundException("User Id " + name + " does not exist");
		log.info("Dispaly register" + userData.toString());
		return new ResponseEntity<>(userData, HttpStatus.OK);
	}

	@DeleteMapping("/user/{name}")
	public ResponseEntity<String> deleteRegister(@PathVariable(value = "name") String name) throws UserNotFoundException {
		userLoginServiceImpl.deleteUser(name.toLowerCase());
		return new ResponseEntity<>("User With id : " + name + " is deleted successfully", HttpStatus.OK);

	}

	@GetMapping("/allusers")
	public Iterable<UserRegistration> getAllUsers() {
		return userRegistrationService.getAllUser();
	}

	@GetMapping("/companies")
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@GetMapping("/allQuizz")

	public List<Quizz> getAllQuizz() {
		return quizzRepository.findAll();
	}
}