package com.example.quiz_1150119.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz_1150119.entity.User;

public interface UserDao extends JpaRepository<User, String>{

	//新增資料
	@Modifying
	@Transactional
	@Query(value = "insert ignore into user(email, name, phone, age) values (?1, ?2, ?3, ?4)",nativeQuery = true)
	public void insit(String email, String name, String phone, int age);
	
	//確認此帳號存不存在
	@Query(value = "select count(email) from user where email = ?1 and phone = ?2",nativeQuery = true)
	public int checkLogin(String email, String phone);
	
	//確認帳號密碼是否正確
	@Query(value = "select * from user where email = ?1 and phone = ?2", nativeQuery = true)
	public User findUserForLogin(String email, String phone);
	
	//用email搜尋user的資料
	@Query(value = "select * from user where email = ?1 ", nativeQuery = true)
	public User getByEmail(String email);
}
