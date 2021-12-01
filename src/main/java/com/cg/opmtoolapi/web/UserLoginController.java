package com.cg.opmtoolapi.web;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.opmtoolapi.domain.UserLogin;
import com.cg.opmtoolapi.exception.UserNotFoundException;
import com.cg.opmtoolapi.exception.WrongPasswordException;
import com.cg.opmtoolapi.repository.UserLoginRepository;
import com.cg.opmtoolapi.service.UserLoginService;
import com.cg.opmtoolapi.serviceimpl.MapValidationErrorService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/onlinepassport")
public class UserLoginController {

	@Autowired
	private UserLoginService userLoginService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	@Autowired
	private UserLoginRepository userRepo;
	
	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@Valid @RequestBody UserLogin login, BindingResult result) {
		UserLogin userLogin;
		Map<String, String> exceptionMap =  new HashMap<>();
		exceptionMap.put("response", "User Is Already Exist");
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		try {
		userLogin = userLoginService.addUser(login);
		}catch(Exception ex) {
			return new ResponseEntity<>(exceptionMap,HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(userLogin,HttpStatus.CREATED);
	}
	

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@Valid @RequestBody UserLogin user,BindingResult result) {
		boolean userLogin;
		Map<String, String> exceptionMap =  new HashMap<>();
		Map<String, String> exceptionMap1 =  new HashMap<>();
		exceptionMap.put("response", "User Does Not Exist");
		exceptionMap1.put("response1", "Wrong Password");
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		try {
			userLogin = userLoginService.authenticate(user);
		}catch(UserNotFoundException ex) {
			return new ResponseEntity<>(exceptionMap,HttpStatus.EXPECTATION_FAILED);
		}catch(WrongPasswordException ex) {
			return new ResponseEntity<>(exceptionMap1,HttpStatus.EXPECTATION_FAILED);
		}
		if (userLogin)
			return new ResponseEntity<>(userLogin,HttpStatus.OK);
		else
			return new ResponseEntity<>("Authentication Failed",HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/changepassword/{name}")
	public ResponseEntity<?> changePassword(@Valid @PathVariable(value = "name") String name, @RequestBody UserLogin login,BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		UserLogin logindb = userLoginService.getLoginByUserName(name.toLowerCase());
		logindb.setPassword(login.getPassword());
		return new ResponseEntity<>(userLoginService.changePassword(logindb), HttpStatus.OK);
	}
	
	@GetMapping("/{userName}")
	public UserLogin getUser(@PathVariable(value="userName") String userName) {
		UserLogin login = userRepo.findByUserName(userName);
		return login;
	}

}
