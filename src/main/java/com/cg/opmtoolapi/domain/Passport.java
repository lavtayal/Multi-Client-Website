package com.cg.opmtoolapi.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="passports")
public class Passport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long passportId;
	@Column(name = "delivery_date")
	@JsonFormat(pattern = "yyyy-MM-dd")

	private LocalDate passportDate;
	@Email
	private String passportMailId;

	private String passportLocation;
	
	

	public Long getPassportId() {
		return passportId;
	}



	public void setPassportId(Long passportId) {
		this.passportId = passportId;
	}



	public LocalDate getPassportDate() {
		return passportDate;
	}



	public void setPassportDate(LocalDate passportDate) {
		this.passportDate = passportDate;
	}



	public String getPassportMailId() {
		return passportMailId;
	}



	public void setPassportMailId(String passportMailId) {
		this.passportMailId = passportMailId;
	}



	public String getPassportLocation() {
		return passportLocation;
	}



	public void setPassportLocation(String passportLocation) {
		this.passportLocation = passportLocation;
	}



	public Passport(@NotBlank(message = "Passport Id can not be blank") Long passportId,
			@NotBlank(message = "Passport date can not be blank") LocalDate passportDate,
			@Email @NotBlank(message = "Passport mail can not be blank") String passportMailId,
			@NotBlank(message = "location can not be blank") String passportLocation) {
		super();
		this.passportId = passportId;
		this.passportDate = passportDate;
		this.passportMailId = passportMailId;
		this.passportLocation = passportLocation;
	}



	public Passport() {
		super();
	}
	

}
