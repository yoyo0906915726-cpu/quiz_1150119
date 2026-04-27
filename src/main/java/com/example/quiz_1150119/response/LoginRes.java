package com.example.quiz_1150119.response;

public class LoginRes extends BasicRes{

	private String email;
	
	private String name;

	public LoginRes(int code, String massage, String name) {
		super(code, massage);
		this.name = name;
	}

	public LoginRes() {
		super();
		
	}

	public LoginRes(int code, String massage) {
		super(code, massage);
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
