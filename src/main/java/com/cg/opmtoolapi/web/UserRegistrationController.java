package com.cg.opmtoolapi.web;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.opmtoolapi.domain.Company;
import com.cg.opmtoolapi.domain.Enquiry;
import com.cg.opmtoolapi.domain.Quizz;
import com.cg.opmtoolapi.domain.UserRegistration;
import com.cg.opmtoolapi.service.FileStorageService;
import com.cg.opmtoolapi.service.UserRegistrationService;
import com.cg.opmtoolapi.serviceimpl.EnquiryServiceImpl;
import com.cg.opmtoolapi.serviceimpl.MailServiceImpl;
import com.cg.opmtoolapi.serviceimpl.MapValidationErrorService;
import com.cg.opmtoolapi.serviceimpl.QuizzServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/onlinepassport")
public class UserRegistrationController {

	@Autowired
	public UserRegistrationService userRegistrationService;

	@Autowired
	public MapValidationErrorService mapValidationErrorService;

	@Autowired
	private FileStorageService storageService;

	@Autowired
	private EnquiryServiceImpl enqService;
	
	@Autowired
	private MailServiceImpl mailServiceImpl;
	
	@Autowired
	private QuizzServiceImpl quizzServieImpl;

	final Logger log = org.slf4j.LoggerFactory.getLogger(UserRegistrationController.class);

	@PostMapping("/{userName}/add-details")
	public ResponseEntity<?> updateUserInfo(@Valid @PathVariable String userName, @RequestBody UserRegistration data,
			BindingResult result) {
		log.info("create a new Register" + data.toString());
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		String message = userRegistrationService.addUserRegistration(userName.toLowerCase(), data);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	
	@PatchMapping("/{userName}/update")
	public ResponseEntity<UserRegistration> updateUserDetails(@PathVariable String userName,@RequestBody UserRegistration data){
		UserRegistration userRegistration = userRegistrationService.update(userName.toLowerCase(),data);
		return new ResponseEntity<>(userRegistration,HttpStatus.OK);
		
	}

	@GetMapping("/all-company")
	public List<Company> getAllCompanies() {
		return userRegistrationService.getAllCompanies();
	}

	@GetMapping("/company/{searchBy}")
	public List<Company> getCompanyBy(@Valid @PathVariable String searchBy) {
		System.out.println(searchBy);
		return userRegistrationService.getCompaniesByResponse(searchBy.toLowerCase());
	}

	@PostMapping("/{userName}/{companyCode}/enquiry")
	public ResponseEntity<String> sendEnquiry(@PathVariable String userName, @PathVariable String companyCode) {
		String response = enqService.createEnquiry(userName.toLowerCase(), companyCode);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/{userName}/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable String userName) {
		
		try {
			System.out.println(file);
			storageService.store(userName.toLowerCase(),file);
			return new ResponseEntity<String>("Uploaded the file successfully: " + file.getOriginalFilename(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Could not upload the file: " + file.getOriginalFilename() + "!",HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/{userName}/refer/{mail}")
	public ResponseEntity<String> referCompanyToFriend(@PathVariable String userName,@PathVariable String mail) throws MessagingException{
		String message = mailServiceImpl.sendEmail(userName.toLowerCase(), mail);
		System.out.println(message);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	@PostMapping("/{userName}/quizz")
	public ResponseEntity<String> attemptQuizz(@PathVariable String userName,@RequestBody Quizz quizz) throws MessagingException{
		quizzServieImpl.addQuiz(userName.toLowerCase(),quizz);
		return new ResponseEntity<>("Created",HttpStatus.OK);
	}
	
	@GetMapping("/status/{userName}")
	public List<Enquiry> getEnquiry(@PathVariable String userName) {
		System.out.println(userName);
		List<Enquiry> resultEnquries = userRegistrationService.getEnquiryByUserName(userName);
		System.out.println(resultEnquries);
		return resultEnquries;
	}
	

}
