package com.cg.opmtoolapi.service;

import java.util.List;

import com.cg.opmtoolapi.domain.Quizz;

public interface QuizzService {
	public void addQuiz(String userName,Quizz quizz);
	
	public Quizz findQuizz(String userName);
	
	public void removeQuizz(String userName);
	
	public List<Quizz> getAllQuizz();

}
