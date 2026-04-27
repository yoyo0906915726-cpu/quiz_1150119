package com.example.quiz_1150119.response;

public class BasicRes {
	/* status code 狀態: 請求服務的結果代碼 */
	private int code;

	/* status code: 請求服務的結果訊息 */
	private String massage;

	public BasicRes(int code, String massage) {
		super();
		this.code = code;
		this.massage = massage;
	}

	public BasicRes() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	
	
}
