package com.cg.opmtoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.opmtoolapi.domain.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin,String> {
	UserLogin findByEmail(String email);
	UserLogin findByUserName(String userName);
}
