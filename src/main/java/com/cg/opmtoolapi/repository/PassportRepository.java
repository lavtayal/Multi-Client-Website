package com.cg.opmtoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.opmtoolapi.domain.Passport;
@Repository
public interface PassportRepository extends JpaRepository<Passport, Long>{
	Passport findByPassportMailId(String passportMailId); 

}
