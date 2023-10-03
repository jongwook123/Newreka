package com.d103.newreka.hottopic.controller;

import com.d103.newreka.hottopic.dto.KeyWordDto;
import com.d103.newreka.hottopic.dto.TimeDto;
import com.d103.newreka.hottopic.service.KeyWordService;
import com.d103.newreka.hottopic.service.TimeService;
import com.d103.newreka.quiz.dto.QuizDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hottopic/keyword")
public class KeyWordController {

    @Autowired
    private KeyWordService keyWordService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody KeyWordDto keyWordDto){
        System.out.println(keyWordDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            keyWordService.saveKeyWord(keyWordDto);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        }catch (Exception e){
            resultMap.put("messege", "fail: "+ e.getClass().getSimpleName());
            System.out.println(e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/keyWordList")
    public ResponseEntity<Map<String, Object>> add() {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            List<KeyWordDto> keyWordList = keyWordService.getKeyWordList()
                    .stream()
                    .map(m -> KeyWordDto.fromEntity(m))
                    .collect(Collectors.toList());
            resultMap.put("quizList", keyWordList);
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
