package com.cg.opmtoolapi.domain;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "enquiries")
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "enquiry_id")
	private String enquiryid;
	
	@Column(name = "enquiry_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date enquirydate;
	
	@Column(name = "enquiry_status")
	private String enquirystatus;
	
	@Column(name = "enquiry_desc")
	private String enquirydesc;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "companyid",updatable = false,unique=false)
	@JsonIgnore
	private Company company;
	

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Enquiry() {
		super();
	}


	public Enquiry(String enquiryid, Date enquirydate, String enquirystatus, String enquirydesc) {
		super();
		this.enquiryid = enquiryid;
		this.enquirydate = enquirydate;
		this.enquirystatus = enquirystatus;
		this.enquirydesc = enquirydesc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnquiryid() {
		return enquiryid;
	}

	public void setEnquiryid(String enquiryid) {
		this.enquiryid = enquiryid;
	}

	public Date getEnquirydate() {
		return enquirydate;
	}

	public void setEnquirydate(Date date) {
		this.enquirydate = date;
	}

	public String getEnquirystatus() {
		return enquirystatus;
	}

	public void setEnquirystatus(String enquirystatus) {
		this.enquirystatus = enquirystatus;
	}

	public String getEnquirydesc() {
		return enquirydesc;
	}

	public void setEnquirydesc(String enquirydesc) {
		this.enquirydesc = enquirydesc;
	}

	@Override
	public String toString() {
		return "Enquiry [Id=" + id + ", enquiryid=" + enquiryid + ", enquirydate=" + enquirydate + ", enquirystatus="
				+ enquirystatus + ", enquirydesc=" + enquirydesc + "]";
	}


}
