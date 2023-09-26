package com.d103.newreka.hottopic.controller;

import com.d103.newreka.hottopic.dto.ArticleDto;
import com.d103.newreka.hottopic.dto.KeyWordDto;
import com.d103.newreka.hottopic.service.ArticleService;
import com.d103.newreka.hottopic.service.KeyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hottopic/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody ArticleDto articleDto){
        System.out.println(articleDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            articleService.saveArticle(articleDto);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        }catch (Exception e){
            resultMap.put("messege", "fail: "+ e.getClass().getSimpleName());
            System.out.println(e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
