package com.cg.opmtoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.opmtoolapi.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
