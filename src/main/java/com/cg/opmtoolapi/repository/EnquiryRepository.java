package com.cg.opmtoolapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.opmtoolapi.domain.Enquiry;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, String>{
	List<Enquiry> findByEnquiryid(String enquiryid);

}