package com.d103.newreka.hottopic.controller;

import com.d103.newreka.hottopic.dto.KeyWordDto;
import com.d103.newreka.hottopic.service.KeyWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hottopic/keyword")
public class KeyWordController {

    @Autowired
    private KeyWordService keyWordService;
    private static final Logger logger = LoggerFactory.getLogger(KeyWordController.class);

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody KeyWordDto keyWordDto) {
        System.out.println(keyWordDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            keyWordService.saveKeyWord(keyWordDto);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
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
            List<KeyWordDto> keyWordList = keyWordService.getKeyWordList();
            resultMap.put("quizList", keyWordList);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            //            logger.error("질문 검색 실패", e);
            resultMap.put("message", "fail: " + e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    /**
     * 시간을 기준으로 키워드 검색
     * 예시: /keyWordList/202310041406
     */
    @GetMapping("/keyWordList/{reqTime}")
    public ResponseEntity<Map<String, Object>> add(@PathVariable String reqTime) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            List<KeyWordDto> keyWordList = keyWordService.getKeyWordListWithTime(reqTime);
            resultMap.put("quizList", keyWordList);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("키워드 검색 실패", e);
            resultMap.put("message", "fail: " + e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
