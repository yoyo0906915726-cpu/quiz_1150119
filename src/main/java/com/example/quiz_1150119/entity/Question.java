package com.example.quiz_1150119.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "question")
@IdClass(value = QuestionId.class)
public class Question {

	@Id
	@Column(name = "quiz_id")
	private int quizId; //題目編號
	
	@Id
	@Column(name = "question_id")
	private int questionId; //表單編號
	
	@Column(name = "question")
	private String question; //題目
	
	@Column(name = "type")
	private String type; //題目類型
	
	@Column(name = "required")
	private boolean required; //是否必填
	
	@Column(name = "options")
	private String options; // 選項內容

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}
	
	
}
