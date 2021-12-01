package com.cg.opmtoolapi.web;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.opmtoolapi.domain.Company;
import com.cg.opmtoolapi.domain.Delievery;
import com.cg.opmtoolapi.domain.Enquiry;
import com.cg.opmtoolapi.domain.FileDB;
import com.cg.opmtoolapi.domain.UserRegistration;
import com.cg.opmtoolapi.serviceimpl.CompanyServiceImpl;
import com.cg.opmtoolapi.serviceimpl.MailServiceImpl;
import com.cg.opmtoolapi.serviceimpl.MapValidationErrorService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/onlinepassport")
public class CompanyController {

	@Autowired
	private CompanyServiceImpl companyServiceImpl;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@Autowired
	private MailServiceImpl mailServiceImpl;

	final Logger log = org.slf4j.LoggerFactory.getLogger(CompanyController.class);

	@PostMapping("/add-company")
	public ResponseEntity<?> addCompany(@Valid @RequestBody Company company, BindingResult result) {
		Company createdCompany;
		Map<String, String> exceptionMap =  new HashMap<>();
		exceptionMap.put("response", "Company already exists");
		log.info("company details:" + company.toString());
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		try {
		createdCompany = companyServiceImpl.createCompany(company);
		}catch(Exception ex) {
			return new ResponseEntity<>(exceptionMap,HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
	}

	@GetMapping("/{companyName}/enquiries")
	public Iterable<Enquiry> getEnquiriesByCompanyCode(@PathVariable String companyName) {
		System.out.println("inside");
		return companyServiceImpl.getEnquiryByCompanyCode(companyName);
	}

	@PostMapping("/{companyName}/enquiries/{mail}/{response}")
	public ResponseEntity<String> replyToUserEnquiry(@PathVariable String companyName, @PathVariable String mail,
			@PathVariable String response) throws MessagingException {

		String message = mailServiceImpl.sendEmail(mail, response, companyName);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}
	
	@GetMapping("/{companyName}/deliveries")
	public List<Delievery> getDeliveries(@PathVariable String companyName) {
		Company company = companyServiceImpl.getCompanyByCompanyCode(companyName);
		return company.getDelievery();

	}

	@PostMapping("/{companyName}/deliveries/{mail}/update")
	public ResponseEntity<String> sendUpdateDelieveryDetails(@PathVariable String companyName, @PathVariable String mail) {
		companyServiceImpl.updateDelievery(companyName, mail);
		return new ResponseEntity<>("Updated", HttpStatus.OK);

	}

	@GetMapping("/{companyName}/enquiries/{mail}/files")
	public ResponseEntity<byte[]> getFile(@PathVariable String companyName, @PathVariable String mail) {
		FileDB fileDB = companyServiceImpl.downloadFile(companyName, mail);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
				.body(fileDB.getData());
	}
	
	@GetMapping("/{code}/enquiries/searchby/{response}")
	public ResponseEntity<List<Enquiry>> getCompanyByResponse(@PathVariable String code,@PathVariable String response) throws ParseException {
		List<Enquiry> enquiry = companyServiceImpl.getUserByResponse(response.toLowerCase(),code);
		return new ResponseEntity<>(enquiry,HttpStatus.OK);
	}
	
	@GetMapping("/{code}/enquiries/passports/{passportno}")
	public ResponseEntity<UserRegistration> getUserByPassportNo(@PathVariable String code,@PathVariable long passportno) {
		UserRegistration userRegistration = companyServiceImpl.getUserByPassport(code,passportno);
		return new ResponseEntity<>(userRegistration,HttpStatus.OK);
	}
	
	
	
}
