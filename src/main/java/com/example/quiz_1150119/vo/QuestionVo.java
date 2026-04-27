package com.example.quiz_1150119.vo;

import java.util.List;

import com.example.quiz_1150119.constants.ValidMessage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class QuestionVo {

	public int quizId; // 題目編號

	@Min(value = 1, message = ValidMessage.QUESTION_ID_ERROR)
	public int questionId; // 表單編號

	@NotBlank(message = ValidMessage.QUESTION_ERROR)
	public String question; // 題目

	@NotBlank(message = ValidMessage.TITLE_ERROR)
	public String type; // 題目類型

	public boolean required; // 是否必填

	//不檢查，因簡答題不會有選項
	public List<String> optionsList; // 選項內容

	public QuestionVo() {
		super();
	}

	public QuestionVo(int quizId,  int questionId, String question,  String type,
			boolean required, List<String> optionsList) {
		super();
		this.quizId = quizId;
		this.questionId = questionId;
		this.question = question;
		this.type = type;
		this.required = required;
		this.optionsList = optionsList;
	}
	
	

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

	public List<String> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(List<String> optionsList) {
		this.optionsList = optionsList;
	}

}
