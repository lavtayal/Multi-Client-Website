package com.cg.opmtoolapi.service;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

@Service
public interface MailService {
	public String sendEmail(String userName, String mail) throws MessagingException;
	public String sendEmail(String mail, String response, String code) throws MessagingException;

}
