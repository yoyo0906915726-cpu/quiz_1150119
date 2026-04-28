package com.example.quiz_1150119.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quiz_1150119.entity.Fillin;
import com.example.quiz_1150119.entity.FillinId;

@Repository
public interface FillinDao extends JpaRepository<Fillin, FillinId>{
	
	@Query(value = "select * from fillin where quiz_id = ?", nativeQuery = true)
	public List<Fillin> getByQuizId(int quizId);
	
	/* 取得問卷的填寫人數 */
	@Query(value = "select count(distinct f.email) from fillin f where f.quiz_id = ?", nativeQuery = true)
	public int countDistinctEmailByQuizId(int quizId);
}
