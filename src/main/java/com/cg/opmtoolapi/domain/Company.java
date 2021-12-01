package com.cg.opmtoolapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "companies")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "vacancies", updatable = true)
	private Long vacancies;

	@Column(name = "Service")
	private int serviceCharge;
	
    @NotBlank(message="Password is a required field")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message = "Must have Digits atleast one lower atleast one upper and a special character")
	private String password;

	@NotBlank(message = "comapny code is required for registration")
	@Column(unique = true, updatable = true)
	private String companyCode;

	@NotBlank(message = "company name is required")
	@Column(unique = true, updatable = true)
	private String companyName;

	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="must be well formed Ex: xyz@gmail.com")
	@NotBlank(message = "company email is required")
	@Email
	private String companyEmail;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "enquiryid")
	private List<Enquiry> enquiry = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Delievery> delievery = new ArrayList<>();

	@NotBlank(message = "company location is required")
	@Column(name = "location", updatable = false)
	private String companyLocation;

	@NotBlank(message = "Type of PassportTitle is required")
	@Column(name = "passportTitle", updatable = false)
	private String passportTitle;

	@NotBlank(message = "Min Qualification is required")
	@Column(name = "minQualification", updatable = true)
	private String minQualifiaction;

	public Long getVacancies() {
		return vacancies;
	}

	public void setVacancies(Long vacancies) {
		this.vacancies = vacancies;
	}

	public int getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(int serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public List<Delievery> getDelievery() {
		return delievery;
	}

	public void setDelievery(List<Delievery> delievery) {
		this.delievery = delievery;
	}

	public List<Enquiry> getEnquiry() {
		return enquiry;
	}

	public void setEnquiry(List<Enquiry> enquiry) {
		this.enquiry = enquiry;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyLocation() {
		return companyLocation;
	}

	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}

	public String getPassportTitle() {
		return passportTitle;
	}

	public void setPassportTitle(String passportTitle) {
		this.passportTitle = passportTitle;
	}

	public String getMinQualifiaction() {
		return minQualifiaction;
	}

	public void setMinQualifiaction(String minQualifiaction) {
		this.minQualifiaction = minQualifiaction;
	}

	public Company(
			@NotBlank(message = "Password is a required field") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Must have Digits atleast one lower atleast one upper and a special character") String password,
			@NotBlank(message = "comapny code is required for registration") String companyCode,
			@NotBlank(message = "company name is required") String companyName,
			@Email @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$") @NotBlank(message = "company name is required") String companyEmail,
			@NotBlank(message = "company location is required") String companyLocation,
			@NotBlank(message = "Type of PassportTitle is required") String passportTitle,
			@NotBlank(message = "Min Qualification is required") String minQualifiaction) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.password = password;
		this.companyLocation = companyLocation;
		this.passportTitle = passportTitle;
		this.minQualifiaction = minQualifiaction;
	}

	public Company() {
		super();
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyCode=" + companyCode + ", companyName=" + companyName + ", companyEmail="
				+ companyEmail + ", companyLocation=" + companyLocation + ", passportTitle=" + passportTitle
				+ ", minQualifiaction=" + minQualifiaction + "]";
	}

}
