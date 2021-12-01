package com.cg.opmtoolapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "quizz")
public class Quizz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "answer1")
	@NotBlank(message = "Answer1 is Required")
	private String answer1;
	
	@Column(name = "answer2")
	@NotBlank(message = "Answer2 is Required")
	private String answer2;
	
	@Column(name = "answer3")
	@NotBlank(message = "Answer3 is Required")
	private String answer3;
	
	@Column(name = "answer4")
	@NotBlank(message = "Answer4 is Required")
	private String answer4;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public Quizz(String answer1, String answer2, String answer3, String answer4) {
		super();
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
	}
	
	public Quizz() {
		super();
	}

}
