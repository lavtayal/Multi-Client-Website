package com.cg.opmtoolapi.domain;

import java.time.LocalDate;

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
@Table(name="deliveries")
public class Delievery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "delivery_number")
	private Long deliveryNumber;
	@Column(name = "deliveryDate")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate deliveryDate;
	@Column(name = "deliveryStatus")
	private String deliveryStatus;
	@Column(name = "delivery_contact")
	private String deliveryContact;
	@Column(name = "remark")
	private String remark;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "delieveryid",updatable = false)
	@JsonIgnore
	private Company company;

	public Long getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setDeliveryNumber(Long deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryContact() {
		return deliveryContact;
	}

	public void setDeliveryContact(String deliveryContact) {
		this.deliveryContact = deliveryContact;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Delievery [deliveryNumber=" + deliveryNumber + ", deliveryDate=" + deliveryDate + ", deliveryStatus="
				+ deliveryStatus + ", deliveryContact=" + deliveryContact + ", remark=" + remark + "]";
	}

	public Delievery(Long deliveryNumber, LocalDate deliveryDate, String deliveryStatus, String deliveryContact,
			String remark) {
		super();
		this.deliveryNumber = deliveryNumber;
		this.deliveryDate = deliveryDate;
		this.deliveryStatus = deliveryStatus;
		this.deliveryContact = deliveryContact;
		this.remark = remark;
	}
	
	public Delievery() {
		super();
	}

}
