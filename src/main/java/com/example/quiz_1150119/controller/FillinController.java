package com.example.quiz_1150119.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_1150119.request.FillinReq;
import com.example.quiz_1150119.response.BasicRes;
import com.example.quiz_1150119.response.FeedbackRes;
import com.example.quiz_1150119.response.StatisticsRes;
import com.example.quiz_1150119.service.FillinService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/quiz")
public class FillinController {

	@Autowired
	private FillinService fillinService;
	
	/* 使用者送出表單到後端資料庫 */ 
	@PostMapping("/fillin")
	public BasicRes fillin(@Valid @RequestBody FillinReq req) {
		return fillinService.fillin(req);
	}
	
	/* 只要沒有@RequestBody 都可以用@GetMapping */
	/* 拿使用者填的問卷答案 */
	@GetMapping("/feedback")
	public FeedbackRes feedback(@RequestParam("quizId") int quizId) {
		return fillinService.feedback(quizId);
	}
	
	/* 統計 */
	@GetMapping("/statistics")
	public StatisticsRes statistics(@RequestParam("quizId") int quizId) {
		return fillinService.statistics(quizId);
	}
}
