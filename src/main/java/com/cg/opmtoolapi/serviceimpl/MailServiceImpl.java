package com.cg.opmtoolapi.serviceimpl;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cg.opmtoolapi.domain.Company;
import com.cg.opmtoolapi.domain.Delievery;
import com.cg.opmtoolapi.domain.Enquiry;
import com.cg.opmtoolapi.domain.UserRegistration;
import com.cg.opmtoolapi.exception.CompanyNotFoundException;
import com.cg.opmtoolapi.exception.MailException;
import com.cg.opmtoolapi.repository.DelieveryRepository;
import com.cg.opmtoolapi.repository.UserRepository;
import com.cg.opmtoolapi.service.MailService;
import com.cg.opmtoolapi.supply.ConstantSupply;

@Transactional
@Service
public class MailServiceImpl implements MailService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EnquiryServiceImpl enquiryServiceImpl;

	@Autowired
	private CompanyServiceImpl companySericeImpl;

	@Autowired
	private DelieveryRepository delieveryRepository;

	@Autowired
	private UserRegistrationServiceImpl userRegistrationImpl;

	@Autowired
	private PassportServiceImpl passportServiceImpl;

	@Autowired
	private ReportServiceImpl reportServiceImpl;

	@Override
	public String sendEmail(String userName, String mail) throws MessagingException {
		UserRegistration userRegistration = userRepository.findByUserName(userName);
		if (userRegistration.getDelievery() != null) {
			String from = userRegistration.getEmail();
			String to = mail;

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			helper.setSubject("Response to your query");
			helper.setFrom(from);

			helper.setTo(to);

			boolean html = true;

			helper.setText("<b>Hey Mate Check this company " + userRegistration.getCompany().getCompanyName()
					+ " My passport is done " + from + "</b>,<br><i> You should try it too</i>", html);

			mailSender.send(message);
			return "referred to your friend successfully";
		} else {
			return "you can not refer right now, get your passport first ";
		}

	}

	@Override
	public String sendEmail(String mail, String response, String code) throws MessagingException {
		UserRegistration userRegistration = userRegistrationImpl.getUserByEmail(mail);
		if (userRegistration.getDelievery() != null) {
			return "Sorry But the User Has already received his/her passport";
		}
		Delievery delievery = null;
		Company company = companySericeImpl.getCompanyByCompanyCode(code);
		if (company == null)
			throw new CompanyNotFoundException("company not found registered");
		String from = company.getCompanyEmail();
		String to = null;
		List<Enquiry> sendEnquiry = company.getEnquiry();
		for (Enquiry enquiry : sendEnquiry) {
			if (enquiry.getEnquiryid().equals(mail)&&enquiry.getEnquirystatus().equals("PENDING")) {
				to = enquiry.getEnquiryid();
			}
			if(enquiry.getEnquiryid().equals(mail)&&enquiry.getEnquirystatus().equals("REJECTED")) {
				return "already responded to this query";
			}
		}
		if (to == null) {
			to = mail;
		}

		List<Enquiry> enquiries = enquiryServiceImpl.getEnquiryById(to);
		Enquiry enquiry = null;
		for (Enquiry each_enquiry : enquiries) {
			if (each_enquiry.getCompany().getCompanyName().equals(code)) {
				enquiry = each_enquiry;
			}
		}
		ByteArrayOutputStream outputStream = null;

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setSubject("Response to your query");
		helper.setFrom(from);

		helper.setTo(to);
		MimeBodyPart textBodyPart = new MimeBodyPart();
		

		boolean html = true;
		if (response.toUpperCase().equals("REJECTED")) {

			helper.setText("<b>Hey your application has been responded by" + from
					+ "</b>,<br><i> and Your application has been rejected</i>", html);

			enquiry.setEnquirystatus(ConstantSupply.REJECTED);
			mailSender.send(message);

			
		} else if (response.toUpperCase().equals("ACCEPTED")) {

			textBodyPart.setText("Hey your enquiry has  been responded by " + from
					+ " and it has been Accepted, So we request you to please"+
					"Pay to amount and please find your Invoice attached below");

			outputStream = new ByteArrayOutputStream();
			reportServiceImpl.writePdf(outputStream, mail, code);
			byte[] bytes = outputStream.toByteArray();
			DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			pdfBodyPart.setFileName("Invoice.pdf");

			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(textBodyPart);
			mimeMultipart.addBodyPart(pdfBodyPart);
			message.setContent(mimeMultipart);
			enquiry.setEnquirystatus(ConstantSupply.SHORT_LISTED);
			delievery = companySericeImpl.createDelievery(enquiry);
			userRegistration.setDelievery(delievery);
			userRegistration.setCompany(company);
			delievery.setCompany(company);
 			delieveryRepository.save(delievery);
			mailSender.send(message);
		} else {
			throw new MailException("No valid response");
		}
		List<Enquiry> updateEnquiry = userRegistration.getEnquiry();

		for (int i = 0; i < updateEnquiry.size(); i++) {
			if (updateEnquiry.get(i).getCompany().getCompanyCode().equals(code)) {
				updateEnquiry.remove(i);
			}
		}
		updateEnquiry.add(enquiry);
		enquiryServiceImpl.updateEnquiry(enquiry);
		userRegistration.setEnquiry(updateEnquiry);
		userRegistrationImpl.update(userRegistration.getUserName(), userRegistration);
		if (userRegistration.getDelievery() != null) {
			passportServiceImpl.addPassport(userRegistration);
			companySericeImpl.updateCompany(company);
		}
		return "Sent";

	}

}
