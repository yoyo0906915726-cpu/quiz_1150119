package com.example.quiz_1150119.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz_1150119.entity.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer>{

	/* 新增問卷 */
	@Modifying
	@Transactional
	@Query(value = "insert into quiz(title, description, star_date, end_date, published)"//
			+ " values(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
	public void insert(String title,String description,LocalDate starDate,//
			LocalDate endDate,boolean published);
	
	/* 找到該問卷對應的內容 */
	@Query(value = "select max(id) from quiz", nativeQuery = true)
	public int getMaxId();
	
	/* 更新問卷題目 */
	@Modifying
	@Transactional
	@Query(value = "update quiz set title = ?2, description = ?3,"//
			+ " star_date = ?4, end_date = ?5, published = ?6 where id = ?1", nativeQuery = true)
	public int update(int id, String title,String description,LocalDate starDate,//
			LocalDate endDate,boolean published);
	
	/* 取得所有問卷 */
	@Query(value = "select * from quiz", nativeQuery = true)
	public List<Quiz> getAll();
	
	//取得所有已發布的 (前台列表頁) published = 1 就是 published is true
	@Query(value = "select * from quiz where published = 1", nativeQuery = true)
	public List<Quiz> getAllPublished();
	
	/* 刪除多個問卷 */
	@Modifying
	@Transactional
	@Query(value = "delete from quiz where id in (?) ", nativeQuery = true)
	public void delete(List<Integer> quizIds);
	
	@Query(value = "select * from quiz where id = ?1", nativeQuery = true)
	public Quiz getById(int id);
	
	/* 檢查填單時，是否符合可填寫時間和狀態 */
	@Query(value = "select * from quiz where id = ?1 and star_date <= ?2 and end_date >= ?2"
			+ " and published = 1", nativeQuery = true)
	public Quiz getPublishedQuizByIdBetween(int id, LocalDate today);
	
	/* 已發布且開始之後的問卷 */
//	@Query(value = "select * from quiz where id = ?1 and star_date <= ?2 and "
//			+ " published = 1", nativeQuery = true)
//	public Quiz getPublishedQuizAfter(int id, LocalDate date);
	
	@Query(value = "select * from quiz where id = ?1 and star_date <= ?2 ", nativeQuery = true)
	public Quiz getPublishedQuizAfter(int id, LocalDate date);
	
	//更新問卷狀態
	@Modifying
	@Transactional
	@Query(value = "update quiz set published = ?2 where id = ?1 ", nativeQuery = true)
	public int updatePublishedStatus(int id, boolean published);
}
