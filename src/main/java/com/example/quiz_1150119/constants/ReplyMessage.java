package com.example.quiz_1150119.constants;

public enum ReplyMessage {
	
	//列舉
	SUCCESS(200, "Success"),//
	LOGIN_SUCCESS(200, "Login success"),//
	PARAM_EMAIL_ERROR(400,"Param email error"),//
	PARAM_NAME_ERROR(400, "Param name error"),//
	PARAM_AGE_ERROR(200, "Param age error"),//
	ENAIL_NOT_FOUND(404, "Email.not found!!"),//
	NO_CONTENT(404, "Email or mobile number cannot be empty!!"),//
	START_DATE_IS_AFTER_END_DATE(400,"start date is after end date!!"),//
	START_DATE_IS_AFTER_TODATE(400,"start date is after todate!!"),//
	TYPE_ERROR(400,"type error"),//
	OPTIONS_IS_EMPTY(400,"options is empty"),//
	OPTIONS_PARSER_ERROR(400,"options parser error"),//
	QUIZ_ID_ERROR(400,"quiz id error!"),//
	QUIZ_ID_MISMACH(400,"quiz id mismach"),//
	QUESTION_NOT_FOUND(400,"Questionnaire not found"),//
	QUIZ_NOT_FOUND(404,"Quiz not found"),//
	ANSWER_IS_REQUIRED(400,"Answer is required!"),//
	ONLY_ONE_ANSWERS_ALLOWED(400,"only one answer allowed"),//
	OPTIONS_ANSWERS_MISMATCH(400,"Options answer mismatch"),//
	ANSWERS_PARSER_ERROR(400,"Answers parser error"),//
	QUIZ_UPDATE_NOT_ALLOWED(400,"Quiz update not allowed !"),//
	Update_failed(400,"Update failed");

	private int code;
	
	private String message;

	private ReplyMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	
}
