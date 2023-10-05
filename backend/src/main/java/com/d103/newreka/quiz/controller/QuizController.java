package com.d103.newreka.quiz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.d103.newreka.jwt.util.JwtUtil;
import com.d103.newreka.security.user.UserDetailsImpl;
import com.d103.newreka.security.user.UserDetailsServiceImpl;
import com.d103.newreka.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d103.newreka.quiz.dto.QuizCompareDto;
import com.d103.newreka.quiz.dto.QuizDto;
import com.d103.newreka.quiz.service.QuizService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestBody QuizDto quizDto) {
		System.out.println(quizDto);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			quizService.saveQuiz(quizDto);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
			System.out.println(e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/quizList")
	public ResponseEntity<Map<String, Object>> add(@RequestParam(required = false) Long keyword) {
		//        String accessToken = request.getHeader("Authorization");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<QuizDto> quizlist = quizService.getQuizeList(keyword)
				.stream()
				.map(m -> QuizDto.fromEntity(m))
				.collect(Collectors.toList());
			resultMap.put("quizList", quizlist);
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			//            logger.error("질문 검색 실패", e);
			resultMap.put("message", "fail: " + e.getClass().getSimpleName());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	/**
	 * 퀴즈 정답 비교 API
	 */
	@PostMapping("/compare")
	public ResponseEntity<Map<String, Object>> quizCompare(@RequestBody QuizCompareDto quizCompareDto, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status;
		try {

			String accessToken = request.getHeader("Authorization");
			String userEmail = jwtUtil.getEmailFromToken(accessToken);
			UserDetailsImpl userDetails = (UserDetailsImpl)userDetailsService.loadUserByUsername(userEmail);
			User user = userDetails.getUser();

			boolean result = quizService.compareAnswer(quizCompareDto, user);
			resultMap.put("message", "success");
			resultMap.put("result", result);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("키워드 검색 실패", e);
			resultMap.put("message", "fail: " + e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
