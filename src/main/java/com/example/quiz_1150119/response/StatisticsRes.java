package com.example.quiz_1150119.response;

import java.util.List;

import com.example.quiz_1150119.vo.AnswersVo;

/* 統計 */
public class StatisticsRes extends BasicRes {

	private List<AnswersVo> answersVoList;
	
	private int  totalCount;//填寫人數

	public StatisticsRes() {
		super();
	}

	public StatisticsRes(int code, String massage) {
		super(code, massage);
	}

	public StatisticsRes(int code, String massage, List<AnswersVo> answersVoList) {
		super(code, massage);
		this.answersVoList = answersVoList;
	}
	
	

	public StatisticsRes(int code, String massage, List<AnswersVo> answersVoList, int totalCount) {
		super(code, massage);
		this.answersVoList = answersVoList;
		this.totalCount = totalCount;
	}

	public List<AnswersVo> getAnswersVoList() {
		return answersVoList;
	}

	public void setAnswersVoList(List<AnswersVo> answersVoList) {
		this.answersVoList = answersVoList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	

}
