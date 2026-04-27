package com.example.quiz_1150119.response;

import java.util.List;

import com.example.quiz_1150119.entity.Quiz;
import com.example.quiz_1150119.vo.QuestionVo;

public class GetQuestionListRes extends BasicRes {

	private List<QuestionVo> questionVoList;
	
	private Quiz quiz;

	public GetQuestionListRes() {
		super();

	}

	public GetQuestionListRes(int code, String massage) {
		super(code, massage);

	}

	public GetQuestionListRes(int code, String massage, List<QuestionVo> questionVoList) {
		super(code, massage);
		this.questionVoList = questionVoList;
	}
	
	public GetQuestionListRes(int code, String massage, List<QuestionVo> questionVoList, Quiz quiz) {
		super(code, massage);
		this.questionVoList = questionVoList;
		this.quiz = quiz;
	}

	public List<QuestionVo> getQuestionVoList() {
		return questionVoList;
	}

	public void setQuestionVoList(List<QuestionVo> questionVoList) {
		this.questionVoList = questionVoList;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	

}
