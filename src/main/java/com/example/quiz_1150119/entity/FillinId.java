package com.example.quiz_1150119.entity;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class FillinId implements Serializable {

	private int quizId;

	private int questionId;

	private String email;

	public FillinId() {
		super();
	}

	public FillinId(int quizId, int questionId, String email) {
		super();
		this.quizId = quizId;
		this.questionId = questionId;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object o) {
		/* 檢查是否為同一個記憶體位址 */
		if (this == o)
			return true;
		/* 檢查物件是否為 null 或類別不一致 */
		if (o == null || getClass() != o.getClass())
			return false;
		/* 轉型後比較欄位內容 */
		FillinId that = (FillinId) o;
		return quizId == that.quizId && questionId == that.questionId &&//
				Objects.equals(email, that.email);
	}

	@Override
	public int hashCode() {
		// 根據欄位內容產生 Hash 值
		return Objects.hash(quizId, questionId, email);
	}

}
