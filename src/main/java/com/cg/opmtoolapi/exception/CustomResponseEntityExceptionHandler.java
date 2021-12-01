package com.cg.opmtoolapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

		UserNotFoundExceptionResponse exceptionResponse = new UserNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public final ResponseEntity<Object> handleEnquiryNotFoundException(EnquiryNotFoundException ex,
			WebRequest request) {

		EnquiryNotFoundExceptionResponse exceptionResponse = new EnquiryNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public final ResponseEntity<Object> handleCompanyNotFoundException(CompanyNotFoundException ex,
			WebRequest request) {

		CompanyNotFoundExceptionResponse exceptionResponse = new CompanyNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException exc) {
		return new ResponseEntity<>("File too large", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleMailException(MailException ex,WebRequest request) {

		MailExceptionResponse exceptionResponse = new MailExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handlePassportNotFoundException(PassportNotFoundException ex,WebRequest request) {

		PassportNotFoundExceptionResponse exceptionResponse = new PassportNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleUserCredFoundException(UserCredentialFoundException ex,WebRequest request) {

		UserCredentialFoundExceptionResponse exceptionResponse = new UserCredentialFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public final ResponseEntity<Object> handleCompanyExistsFoundException(CompanyAlreadyExistsException ex,WebRequest request) {

		CompanyAlreadyExistsExceptionResponse exceptionResponse = new CompanyAlreadyExistsExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public final ResponseEntity<Object> handleWrongPasswordException(WrongPasswordException ex,WebRequest request) {

		WrongPasswordExceptionResponse exceptionResponse = new WrongPasswordExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
