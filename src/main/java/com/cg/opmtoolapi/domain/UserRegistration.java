package com.cg.opmtoolapi.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "users_info")
public class UserRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "userName",unique= true,updatable = false)
	private String userName;

	@Column(name = "firstName",updatable = false)
	@NotNull(message = "First Name is required")
	private String firstName;

	@Column(name = "lastName",updatable = false)
	@NotNull(message = "Last Name is required")
	private String lastName;

	@Column(name = "DateofBirth",updatable = false)
	@NotNull(message = "Date Of Birth is required")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	@Column(name = "gender",updatable = false)
	@NotNull(message = "Gender is required")
	private String gender;
	
	@Column(name = "email",unique= true,updatable = false)
	private String email;

	@Column(name = "UG",updatable = false)
	@NotNull(message = "UG is required")
	private String qualification1;

	@Column(name = "secondary_education",updatable = false)
	@NotNull(message = "secondary education details are required")
	private String qualification2;

	@Column(name = "high_school",updatable = false)
	@NotNull(message = "high school details are required")
	private String qualification3;

	@Column(name = "plotNo",updatable = false)
	@NotNull(message = "PlotNo is required")
	private String plotNo;

	@Column(name = "street",updatable = false)
	@NotNull(message = "Street is required")
	private String street;

	@Column(name = "city",updatable = false)
	@NotNull(message = "City is required")
	private String city;

	@Column(name = "districts",updatable = false)
	@NotNull(message = "District is required")
	private String districts;

	@Column(name = "state",updatable = false)
	@NotNull(message = "State is required")
	private String state;
	
	@Column(name="passportNo")
	private Long passportNo;

	@Column(name = "zipcode",updatable = false)
	@NotNull(message = "Zipp Code is required")
	private Long zipcode;

	@Column(name = "mobileNo",unique= true)
	@NotNull(message = "Mobile No. is required")
	private Long mobileNo;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "enquiryid")
	private List<Enquiry> enquiry = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private FileDB fileDB;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Quizz quizz;
	

	public Long getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(Long passportNo) {
		this.passportNo = passportNo;
	}	

	public Quizz getQuizz() {
		return quizz;
	}

	public void setQuizz(Quizz quizz) {
		this.quizz = quizz;
	}

	public FileDB getFileDB() {
		return fileDB;
	}

	public void setFileDB(FileDB fileDB) {
		this.fileDB = fileDB;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	private Delievery delievery;

	public Delievery getDelievery() {
		return delievery;
	}

	public void setDelievery(Delievery delievery) {
		this.delievery = delievery;
	}

	
	@OneToOne(cascade = CascadeType.ALL)
	private Company company;


	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Enquiry> getEnquiry() {
		return enquiry;
	}

	public void setEnquiry(List<Enquiry> enquiry) {
		this.enquiry = enquiry;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQualification1() {
		return qualification1;
	}

	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}

	public String getQualification2() {
		return qualification2;
	}

	public void setQualification2(String qualification2) {
		this.qualification2 = qualification2;
	}

	public String getQualification3() {
		return qualification3;
	}

	public void setQualification3(String qualification3) {
		this.qualification3 = qualification3;
	}

	public String getPlotNo() {
		return plotNo;
	}

	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistricts() {
		return districts;
	}

	public void setDistricts(String districts) {
		this.districts = districts;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getZipcode() {
		return zipcode;
	}

	public void setZipcode(Long zipcode) {
		this.zipcode = zipcode;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public UserRegistration(Long id, String firstName, String lastName, LocalDate dateOfBirth, String gender,
			String email, String qualification1, String qualification2, String qualification3, String plotNo,
			String street, String city, String districts, String state, Long zipcode, Long mobileNo) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.email = email;
		this.qualification1 = qualification1;
		this.qualification2 = qualification2;
		this.qualification3 = qualification3;
		this.plotNo = plotNo;
		this.street = street;
		this.city = city;
		this.districts = districts;
		this.state = state;
		this.zipcode = zipcode;
		this.mobileNo = mobileNo;
	}
	public UserRegistration() {
		super();
	}

	@Override
	public String toString() {
		return "PassportRegistrationModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", email=" + email + ", qualification1="
				+ qualification1 + ", qualification2=" + qualification2 + ", qualification3=" + qualification3
				+ ", plotNo=" + plotNo + ", street=" + street + ", city=" + city + ", districts=" + districts
				+ ", state=" + state + ", zipcode=" + zipcode + ", mobileNo=" + mobileNo + "]";
	}

}
