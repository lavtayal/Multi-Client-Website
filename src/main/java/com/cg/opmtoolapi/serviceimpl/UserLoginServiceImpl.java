package com.cg.opmtoolapi.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.opmtoolapi.domain.UserLogin;
import com.cg.opmtoolapi.exception.UserCredentialFoundException;
import com.cg.opmtoolapi.exception.UserNotFoundException;
import com.cg.opmtoolapi.exception.WrongPasswordException;
import com.cg.opmtoolapi.repository.UserLoginRepository;
import com.cg.opmtoolapi.service.UserLoginService;
@Transactional
@Service
public class UserLoginServiceImpl implements UserLoginService {
	@Autowired
	private UserLoginRepository userLoginRepository;

	@Override
	public boolean authenticate(UserLogin login) {

		UserLogin login1 = userLoginRepository.findByUserName(login.getUserName());
		if(login1==null) {
			throw new UserNotFoundException("User Not Found");
		}
//		if ((login.getPassword().equals(login1.getPassword())&&login.getUserName().equals(login1.getUserName())))
		if(login.getPassword().equals(login1.getPassword()))
		{
			if(login.getUserName().equals(login1.getUserName())){
				return true;	
			}
		}
		else {
			throw new WrongPasswordException("Wrong Password");
		}
		return true;
	}

	@Override
	public UserLogin changePassword(UserLogin login) {
		return userLoginRepository.save(login);
	}

	@Override
	public UserLogin getLoginByUserName(String name) {
		return userLoginRepository.findByUserName(name);
	}

	@Override
	public UserLogin addUser(UserLogin login) {
		UserLogin userName= userLoginRepository.findByUserName(login.getUserName());
		//UserLogin userEmail = userLoginRepository.findByEmail(login.getEmail());
		if(userName!=null) //|| userEmail!=null) 
				{
			throw new UserCredentialFoundException("User is already exist");
		}
		login.setUser(true);
		return userLoginRepository.save(login);
	}

	@Override
	public void update(String userName,UserLogin userLogin) {
		UserLogin user = userLoginRepository.findByEmail(userLogin.getEmail());
		if(user==null)
			throw new UserNotFoundException("User Not Found for this id");	
		
		user.setUserName(userLogin.getUserName());
		if(userLogin.getEmail()!=null)
			user.setEmail(userLogin.getEmail());
		if(userLogin.getPassword()!=null)
			user.setPassword(userLogin.getPassword());
		if(userLogin.isCompleted()==true)
			user.setCompleted(true);
		userLoginRepository.save(user);	
	}

	@Override
	public void deleteUser(String name) {
		UserLogin user = userLoginRepository.findByUserName(name);
		userLoginRepository.delete(user);
		
	}

	@Override
	public UserLogin addEmployee(UserLogin login) {
		UserLogin userName= userLoginRepository.findByUserName(login.getUserName());
		UserLogin userEmail = userLoginRepository.findByEmail(login.getEmail());
		if(userName!=null || userEmail!=null) {
			throw new UserCredentialFoundException("Employee is already exist");
		}
		login.setEmployee(true);
		return userLoginRepository.save(login);
	}

}
