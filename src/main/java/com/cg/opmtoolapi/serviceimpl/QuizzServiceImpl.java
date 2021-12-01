package com.cg.opmtoolapi.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.opmtoolapi.domain.Quizz;
import com.cg.opmtoolapi.domain.UserRegistration;
import com.cg.opmtoolapi.exception.UserNotFoundException;
import com.cg.opmtoolapi.repository.QuizzRepository;
import com.cg.opmtoolapi.service.QuizzService;
@Transactional
@Service
public class QuizzServiceImpl implements QuizzService{
	
	@Autowired
	private QuizzRepository quizzRepo;
	
	@Autowired
	private UserRegistrationServiceImpl userRegistrationServiceImpl;

	@Override
	public void addQuiz(String userName,Quizz quizz) {
		UserRegistration user = userRegistrationServiceImpl.getUserByUserName(userName);
		if(user==null)
			throw new UserNotFoundException("User not found for this user name");
		quizzRepo.save(quizz);
		user.setQuizz(quizz);
		userRegistrationServiceImpl.update(userName, user);
	}

	@Override
	public Quizz findQuizz(String userName) {
		UserRegistration user = userRegistrationServiceImpl.getUserByUserName(userName);
		if(user==null)
			throw new UserNotFoundException("User not found for this user name");
		
		return user.getQuizz();
	}

	@Override
	public void removeQuizz(String userName) {
		UserRegistration user = userRegistrationServiceImpl.getUserByUserName(userName);
		if(user==null)
			throw new UserNotFoundException("User not found for this user name");
		
		quizzRepo.delete(user.getQuizz());
	}

	@Override
	public List<Quizz> getAllQuizz() {
		return quizzRepo.findAll();
	}
	
	

}
