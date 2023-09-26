package com.d103.newreka.quiz.controller;

import com.d103.newreka.hottopic.dto.TimeDto;
import com.d103.newreka.hottopic.service.TimeService;
import com.d103.newreka.quiz.domain.Quiz;
import com.d103.newreka.quiz.dto.QuizDto;
import com.d103.newreka.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody QuizDto quizDto){
        System.out.println(quizDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            quizService.saveQuiz(quizDto);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        }catch (Exception e){
            resultMap.put("messege", "fail: "+ e.getClass().getSimpleName());
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
}
