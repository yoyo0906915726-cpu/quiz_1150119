package com.example.quiz_1150119.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_1150119.request.CreateQuizReq;
import com.example.quiz_1150119.request.UpdateQuizReq;
import com.example.quiz_1150119.response.BasicRes;
import com.example.quiz_1150119.response.GetQuestionListRes;
import com.example.quiz_1150119.response.GetQuizListRes;
import com.example.quiz_1150119.service.QuizService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	/* @Valid: 是為了讓 CreateQuizReq 中的屬性限制生效 */
	/* 新增問卷 */
	@PostMapping("/create")
	public BasicRes create(@Valid @RequestBody CreateQuizReq req) throws Exception {
		return quizService.create(req);
	}
	
	/* 更新問卷 */
	@PostMapping("/update")
	public BasicRes update(@Valid @RequestBody UpdateQuizReq req) {
		return quizService.update(req);
	}
	
	/* 取問卷列表 */
	@GetMapping("/get_quiz_list")
	public GetQuizListRes getList(@RequestParam("isFrontend") boolean isFrontend) {
		return quizService.getList(isFrontend);
	}
	
	/* API 路徑: http://localhost:8080/quiz/get_question_list?quizId=1
	 * ? 後面的 quizId 是 @RequestParam 中的字串*/
	/* 取該列表的問題內容 */
	@GetMapping("/get_questions")
	public GetQuestionListRes getQuestionByQuizId(@RequestParam("quizId") int quizId) {
		return quizService.getQuestionByQuizId(quizId);
	}
	
	/*相同的 key 但會有多個值時，API 路徑，問號後面的格式會是: ?quizId=1&quizId=2&quizId=3，
	 * 多個參數時用 & 串接*/
	/* 刪除問卷 */
	@GetMapping("/delete")
	public BasicRes delete(@RequestParam("quizId") List<Integer> quizIdList) {
		return quizService.delete(quizIdList);
	}
	
	
}
