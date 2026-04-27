package com.example.quiz_1150119.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.quiz_1150119.constants.ReplyMessage;
import com.example.quiz_1150119.dao.UserDao;
import com.example.quiz_1150119.entity.User;
import com.example.quiz_1150119.response.BasicRes;
import com.example.quiz_1150119.response.LoginRes;


@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//新增資料
	public BasicRes addInfo(String email, String name, String phone, int age) {
		/* 參數檢查 */
		if(!StringUtils.hasText(email)) {
			return new BasicRes(ReplyMessage.PARAM_EMAIL_ERROR.getCode(),//
					ReplyMessage.PARAM_EMAIL_ERROR.getMessage());
		}
		if(!StringUtils.hasText(name)) {
			return new BasicRes(ReplyMessage.PARAM_NAME_ERROR.getCode(),//
					ReplyMessage.PARAM_NAME_ERROR.getMessage());
		}
		if(age<0) {
			return new BasicRes(ReplyMessage.PARAM_AGE_ERROR.getCode(),//
					ReplyMessage.PARAM_AGE_ERROR.getMessage());
		}
		userDao.insit(email, name, phone, age);
		return new BasicRes(ReplyMessage.SUCCESS.getCode(),ReplyMessage.SUCCESS.getMessage());
	}
	
	//管理者登入
	public LoginRes login(String email, String phone) {
		if(!StringUtils.hasText(email)||!StringUtils.hasText(phone)) {
			return new LoginRes(ReplyMessage.NO_CONTENT.getCode(),//
					ReplyMessage.NO_CONTENT.getMessage());
		}
		User user = userDao.findUserForLogin(email, phone);
		if(user == null) {
			return new LoginRes(ReplyMessage.ENAIL_NOT_FOUND.getCode(),ReplyMessage.ENAIL_NOT_FOUND.getMessage());
		}
		return new LoginRes(ReplyMessage.LOGIN_SUCCESS.getCode(),ReplyMessage.LOGIN_SUCCESS.getMessage(),user.getName());
	}
	
}
