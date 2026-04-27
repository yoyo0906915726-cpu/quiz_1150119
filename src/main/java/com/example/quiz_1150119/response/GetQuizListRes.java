package com.example.quiz_1150119.response;

import java.util.List;

import com.example.quiz_1150119.entity.Quiz;

public class GetQuizListRes extends BasicRes {

	public List<Quiz> quizList;

	public GetQuizListRes() {
		super();
	}

	public GetQuizListRes(int code, String massage) {
		super(code, massage);
	}

	public GetQuizListRes(int code, String massage, List<Quiz> quizList) {
		super(code, massage);
		this.quizList = quizList;
	}

	public List<Quiz> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<Quiz> quizList) {
		this.quizList = quizList;
	}

}
