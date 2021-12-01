package com.cg.opmtoolapi.serviceimpl;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.opmtoolapi.domain.Company;
import com.cg.opmtoolapi.domain.UserRegistration;
import com.cg.opmtoolapi.repository.UserRepository;
import com.cg.opmtoolapi.service.ReportService;
@Transactional
@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CompanyServiceImpl companyServiceImpl;
	private List<UserRegistration> listUserRegistrations;
	private List<Company> companies;

	public ReportServiceImpl(List<UserRegistration> listUserRegistraions, List<Company> companies) {
		this.listUserRegistrations = listUserRegistraions;
		this.companies = companies;
	}

	private void writeTableHeader(PdfPTable table, String code) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		if (code.equals("1")) {
			cell.setPhrase(new Phrase("Email", font));

			table.addCell(cell);

			cell.setPhrase(new Phrase("First Name", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Last Name", font));
			table.addCell(cell);
		} else if (code.equals("2")) {
			cell.setPhrase(new Phrase("Code", font));

			table.addCell(cell);

			cell.setPhrase(new Phrase("Company Name", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Company location", font));
			table.addCell(cell);

		} else {
			cell.setPhrase(new Phrase("User name", font));

			table.addCell(cell);

			cell.setPhrase(new Phrase("Company Name", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Address", font));
			table.addCell(cell);

		}
	}

	private void writeTableData(PdfPTable table, String code) {
		if (code.equals("1")) {
			for (UserRegistration userRegistration : listUserRegistrations) {
				table.addCell(userRegistration.getEmail());
				table.addCell(userRegistration.getFirstName());
				table.addCell(userRegistration.getLastName());
			}
		} else if (code.equals("2")) {
			for (Company company : companies) {
				table.addCell(company.getCompanyCode());
				table.addCell(company.getCompanyName());
				table.addCell(company.getCompanyLocation());
			}

		} else {
			for (UserRegistration userRegistration : listUserRegistrations) {
				if (userRegistration.getCompany() != null) {
					table.addCell(userRegistration.getUserName());
					table.addCell(userRegistration.getCompany().getCompanyName());
					table.addCell(userRegistration.getStreet());

				}
			}

		}
	}

	public Document export(HttpServletResponse response, String code) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		if (code.equals("1")) {
			Paragraph p = new Paragraph("List of Users", font);
			p.setAlignment(Paragraph.ALIGN_CENTER);

			document.add(p);

			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100f);
			table.setWidths(new float[] { 3.5f, 1.5f, 1.5f });
			table.setSpacingBefore(10);

			writeTableHeader(table, code);
			writeTableData(table, code);
			document.add(table);
		} else if (code.equals("2")) {
			Paragraph p = new Paragraph("List of Companies", font);
			p.setAlignment(Paragraph.ALIGN_CENTER);

			document.add(p);

			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100f);
			table.setWidths(new float[] { 3.5f, 1.5f, 1.5f });
			table.setSpacingBefore(10);

			writeTableHeader(table, code);
			writeTableData(table, code);
			document.add(table);

		} else if(code.equals("3")){
			Paragraph p = new Paragraph("List of Passport recieved Users", font);
			p.setAlignment(Paragraph.ALIGN_CENTER);

			document.add(p);

			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100f);
			table.setWidths(new float[] { 3.5f, 1.5f, 1.5f });
			table.setSpacingBefore(10);

			writeTableHeader(table, code);
			writeTableData(table, code);
			document.add(table);
		}
		else {
			Paragraph p = new Paragraph("In Process", font);
			p.setAlignment(Paragraph.ALIGN_CENTER);

			document.add(p);
			
		}

		document.close();
		return document;

	}


	public void writePdf(ByteArrayOutputStream outputStream, String mail, String code) {
		UserRegistration user = userRepo.findByEmail(mail);
		Company company = companyServiceImpl.getCompanyByCompanyCode(code);
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, outputStream);
		document.open();
		Paragraph paragraph = new Paragraph();

		paragraph.add(new Chunk("INVOICE"));
		paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(paragraph);
		Paragraph paragraph2 = new Paragraph();
		paragraph2.add(new Chunk("Company code:"+code));
		paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(paragraph2);
	    Paragraph paragraph5 = new Paragraph();
		paragraph5.add(new Chunk("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));
		paragraph5.setAlignment(Paragraph.ALIGN_MIDDLE);

		document.add(paragraph5);
	    Paragraph paragraph3 = new Paragraph();
		paragraph3.add(new Chunk("Name                                City                         Service Charge \n\n\n\n"));
		paragraph3.setAlignment(Paragraph.ALIGN_MIDDLE);
		document.add(paragraph3);
		Paragraph paragraph4 = new Paragraph();
		paragraph4.add(new Chunk(user.getFirstName()+"                              "+user.getCity()+"                                "+company.getServiceCharge()));
		paragraph4.setAlignment(Paragraph.ALIGN_MIDDLE);
		document.add(paragraph4);

		document.close();

	}

}
