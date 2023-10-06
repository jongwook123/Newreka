package com.d103.newreka.hottopic.controller;

import com.d103.newreka.hottopic.dto.ArticleDto;
import com.d103.newreka.hottopic.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hottopic/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody ArticleDto articleDto) {
//        System.out.println(articleDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            articleService.saveArticle(articleDto);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
            System.out.println(e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/articleList")
    public ResponseEntity<Map<String, Object>> add(@RequestParam(required = false) Long keyword) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            List<ArticleDto> articleList = articleService.getArticleList(keyword)
                    .stream()
                    .map(m -> ArticleDto.fromEntity(m))
                    .collect(Collectors.toList());
            resultMap.put("quizList", articleList);
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
