package com.cg.opmtoolapi.service;


import com.cg.opmtoolapi.domain.UserLogin;

public interface UserLoginService {

	public UserLogin getLoginByUserName(String name);

	public boolean authenticate(UserLogin login);

	public UserLogin changePassword(UserLogin login);

	public UserLogin addUser(UserLogin login);
	
	public UserLogin addEmployee(UserLogin login);

	public void update(String userName,UserLogin userLogin);
	
	public void deleteUser(String name);

}
