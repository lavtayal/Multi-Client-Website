package com.cg.opmtoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.opmtoolapi.domain.UserRegistration;
@Repository
public interface UserRepository extends JpaRepository<UserRegistration,String>{
	UserRegistration findByEmail(String email);
	UserRegistration findByUserName(String userName);
	UserRegistration findByMobileNo(Long mobileNo);
}
