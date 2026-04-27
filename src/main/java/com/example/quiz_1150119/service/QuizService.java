package com.example.quiz_1150119.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.quiz_1150119.constants.ReplyMessage;
import com.example.quiz_1150119.constants.Type;
import com.example.quiz_1150119.dao.QuestionDao;
import com.example.quiz_1150119.dao.QuizDao;
import com.example.quiz_1150119.entity.Question;
import com.example.quiz_1150119.entity.Quiz;
import com.example.quiz_1150119.request.CreateQuizReq;
import com.example.quiz_1150119.request.UpdateQuizReq;
import com.example.quiz_1150119.response.BasicRes;
import com.example.quiz_1150119.response.GetQuestionListRes;
import com.example.quiz_1150119.response.GetQuizListRes;
import com.example.quiz_1150119.vo.QuestionVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuizService {
	
	/* 物件(類別)、字串轉換工具 */
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private QuizDao quizDao;
	
	@Autowired
	private QuestionDao questionDao;
	
	/* 創問卷 */
	/* 一個方法中若有使用到多個 Dao 或是同一個 Dao 有呼叫多次去對資料作變更(新增、修改、刪除)，
	 * 必須要用@Transactional，因為這些 Dao 的操作，都屬於同一次的操作，因此資料的變更要嘛全部成功，
	 * 不然就全部都不成功，回溯到尚未變更之前*/
	@Transactional
	public BasicRes create(CreateQuizReq req) {
		/* 檢查格式是否正確(跳到內部檢方法 checkDateAndType()) */
		Quiz quiz = req.getQuiz();
		List<QuestionVo> questionVoList = req.getQuestionVoList();
		BasicRes checkres = checkDateAndType(quiz, questionVoList);
		if(checkres!=null) {
			return checkres;
		}
		
		/*1. 儲存 Quiz: id是流水號，所以不用帶 */
		quizDao.insert(quiz.getTitle(), quiz.getDescription(), quiz.getStarDate(),//
				quiz.getEndDate(), quiz.isPublished());
		
		/*2. 取得 quiz 最新id編號 */
		int quizId = quizDao.getMaxId();
		for(QuestionVo vo : questionVoList) {
			/*3. 把List<String> 轉換成 string，因為 mySQL 不能存 List */
			// 類別中都會有 toString() 這個方法，這裡不能使用 toString() 將 vo.getOptionsList()
			// 直接轉成字串，因為使用此方式轉換得到的字串無法再被轉回成原本的資料型態(基本資料型態除外)
			try {
				String options = mapper.writeValueAsString(vo.getOptionsList());
				/*4. 存question*/
				questionDao.insit(quizId, vo.getQuestionId(), vo.getQuestion(),//
						vo.getType(), vo.isRequired(), options);
			} catch (Exception e) {
				return new BasicRes(ReplyMessage.OPTIONS_PARSER_ERROR.getCode(),ReplyMessage.OPTIONS_PARSER_ERROR.getMessage());
			}
		}
		return new BasicRes(ReplyMessage.SUCCESS.getCode(),ReplyMessage.SUCCESS.getMessage());
	}
	
	/* 私有方法: 檢查格式是否正確 */
	private BasicRes checkDateAndType(Quiz quiz,List<QuestionVo> questionVoList) {
		/* 開始日期不能在結束日期之後 */
		if(quiz.getStarDate().isAfter(quiz.getEndDate())) {
			return new BasicRes(ReplyMessage.START_DATE_IS_AFTER_END_DATE.getCode(),//
					ReplyMessage.START_DATE_IS_AFTER_END_DATE.getMessage());
		}
		/* 開始日期只能從今天開始 */
		if(quiz.getStarDate().isBefore(LocalDate.now())) {
			return new BasicRes(ReplyMessage.START_DATE_IS_AFTER_TODATE.getCode(),//
					ReplyMessage.START_DATE_IS_AFTER_TODATE.getMessage());
		}
		
		/* 判斷 type 種類 */
		for(QuestionVo vo: questionVoList) {
			/* 如果總類的類別不在選項內 */
			if(Type.click(vo.getType()) == false) {
				return new BasicRes(ReplyMessage.TYPE_ERROR.getCode(),//
						ReplyMessage.TYPE_ERROR.getMessage());
			}
			/* 選擇題的情形下，一定要有選項(選項不能為 null 或 空)
			 * => 選擇類型是(null 或 空) => 回傳錯誤訊息 */
			if(Type.isChoice(vo.getType()) && CollectionUtils.isEmpty(vo.getOptionsList())) {
				return new BasicRes(ReplyMessage.OPTIONS_IS_EMPTY.getCode(),//
						ReplyMessage.OPTIONS_IS_EMPTY.getMessage());
			}
		}
		return null;
	}
	
	/* 更新 */
	public BasicRes update(UpdateQuizReq req) {
		/* 檢查quiz 中的id */
		int quizId = req.getQuiz().getId();
		if(quizId<=0) {
			return new BasicRes(ReplyMessage.QUIZ_ID_ERROR.getCode(),ReplyMessage.QUIZ_ID_ERROR.getMessage());
		}
		/* 檢查questionVo中的quizId 是否等於 quiz 中的id */
		for(QuestionVo vo : req.getQuestionVoList()) {
			if(vo.getQuizId()!=quizId) {
				return new BasicRes(ReplyMessage.QUIZ_ID_MISMACH.getCode(),ReplyMessage.QUIZ_ID_MISMACH.getMessage());
			}
		}
		
		/* 檢查格式是否正確(跳到內部檢方法 checkDateAndType()) */
		Quiz quiz = req.getQuiz();
		List<QuestionVo> questionVoList = req.getQuestionVoList();
		BasicRes checkres = checkDateAndType(quiz, questionVoList);
		if(checkres!=null) {
			return checkres;
		}
		
		/* 檢查問卷是否可以更新: 若問卷是已發布，且今天不能是在開始日期當天以及之後 */
		if(quizDao.getPublishedQuizAfter(quizId, LocalDate.now()) != null) {
			/* dao的結果不等於null，表示有找到符合的資料 => 但因要更新，所以不應該找到資料 */
			return new BasicRes(ReplyMessage.QUIZ_UPDATE_NOT_ALLOWED.getCode(),//
					ReplyMessage.QUIZ_UPDATE_NOT_ALLOWED.getMessage());
		}
		
		/* 更新資料 */
		/* 1.更新quiz */
		quizDao.update(quizId,quiz.getTitle(),quiz.getDescription(),quiz.getStarDate(),//
				quiz.getEndDate(),quiz.isPublished());
		/* 2.更新question: *先刪除*舊資料，*再新增*更新後的資料(更新後的資料也算是全新資料) */
//		questionDao.delete(new ArrayList<>(quizId));
		// 2-1. 拿一個空的「水桶」(List)，準備裝要刪除的 ID
		List<Integer> quizIdList = new ArrayList<>();
		// 2-2. 把你要刪除的那張問卷編號 (quizId) 丟進水桶裡
		quizIdList.add(quizId); 
		// 2-3. 把這桶裝有編號的「清單」交給 DAO 去執行刪除
		questionDao.delete(quizIdList);
		for(QuestionVo vo : questionVoList) {
			/*3. 把List<String> 轉換成 string，因為 mySQL 不能存 List */
			try {
				String options = mapper.writeValueAsString(vo.getOptionsList());
				/*4. 存question*/
				questionDao.insit(quizId, vo.getQuestionId(), vo.getQuestion(),//
						vo.getType(), vo.isRequired(), options);
			} catch (Exception e) {
				e.printStackTrace();
				return new BasicRes(ReplyMessage.OPTIONS_PARSER_ERROR.getCode(),//
						ReplyMessage.OPTIONS_PARSER_ERROR.getMessage());
			}
		}
		return new BasicRes(ReplyMessage.SUCCESS.getCode(),ReplyMessage.SUCCESS.getMessage());
	}

//	/* 顯示全部列表頁 */
//	public GetQuizListRes getList() {
//		return new GetQuizListRes(ReplyMessage.SUCCESS.getCode(),ReplyMessage.SUCCESS.getMessage(),//
//				quizDao.getAll());
//	}
	
	/* 顯示前台列表頁 */
	public GetQuizListRes getList(boolean isFrotend) {
		if(isFrotend) { // 等同於 isFrotend == true
			/* 前台列表頁=> 取得已發佈的問卷 */
			return new GetQuizListRes(ReplyMessage.SUCCESS.getCode(),ReplyMessage.SUCCESS.getMessage(),//
					quizDao.getAllPublished());
		}
		/* 顯示全部列表頁(後台)，包含未發布的 */
		return new GetQuizListRes(ReplyMessage.SUCCESS.getCode(),ReplyMessage.SUCCESS.getMessage(),//
				quizDao.getAll());
	}
	
	/* 拿問卷的id取問卷內容 */
	public GetQuestionListRes getQuestionByQuizId(int quizId) {
		/* 檢查參數 */
		if(quizId<=0) {
			return new GetQuestionListRes(ReplyMessage.QUIZ_ID_ERROR.getCode(),//
					ReplyMessage.QUIZ_ID_ERROR.getMessage());
		}
		
		//取問卷主體 (標題、描述、日期)
		Optional<Quiz> quizOp = quizDao.findById(quizId);
		if(quizOp.isEmpty()) {
			return new GetQuestionListRes(ReplyMessage.QUESTION_NOT_FOUND.getCode(),//
					ReplyMessage.QUESTION_NOT_FOUND.getMessage());
		}
		Quiz quiz = quizOp.get();
		
		/* 取同一張問卷下的所有問題 */
		List<Question> questionList =questionDao.getByQuizId(quizId);
		/* 把Question轉換成QuestionVo */
		List<QuestionVo> voList = new ArrayList<>();
		for(Question item : questionList) {
			/* 把 Question 的字串 options 轉換回 List<String> */
			try {
				List<String> optionsList = mapper.readValue(item.getOptions(), new TypeReference<>() {
				});
				/* 把Question 轉換成 QuestionVo*/
				QuestionVo vo = new QuestionVo(quizId, item.getQuestionId(), item.getQuestion(),//
						item.getType(), item.isRequired(), optionsList );
				/* 把轉換後的每一個 vo 新增到 voList 中 */
				voList.add(vo);
			} catch (Exception e) {
				return new GetQuestionListRes(ReplyMessage.OPTIONS_PARSER_ERROR.getCode(),//
						ReplyMessage.OPTIONS_PARSER_ERROR.getMessage());
			}
		}
		return new GetQuestionListRes(ReplyMessage.SUCCESS.getCode(),//
				ReplyMessage.SUCCESS.getMessage(), voList, quiz);
	}
	
	/* 刪除表單 */
	@Transactional
	public BasicRes delete(List<Integer> quizIdList) {
		quizDao.delete(quizIdList);
		questionDao.delete(quizIdList);
		return new GetQuestionListRes(ReplyMessage.SUCCESS.getCode(),ReplyMessage.SUCCESS.getMessage());
	}
}
