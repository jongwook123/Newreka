package com.d103.newreka.scrap.controller;

import com.d103.newreka.scrap.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scrap")
public class ScrapController {

    private final ScrapService scrapService;

    /** 스크랩 조회 */
    @GetMapping("/")
    public ResponseEntity<?> findScrap(@RequestParam("searchBy")) {
        HashMap<String, String> result = new HashMap<>();
        result.put("message", "success");

    }
}
