package com.d103.newreka.hottopic.controller;

import com.d103.newreka.hottopic.dto.TimeDto;
import com.d103.newreka.hottopic.service.TimeService;
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
@RequestMapping("/hottopic/time")
public class TimeController {

    @Autowired
    private TimeService timeService;

//    public TimeController(TimeService timeService) {
//        this.timeService = timeService;
//    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody TimeDto timeDto){
//        System.out.println("hahahaha");
        System.out.println(timeDto);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            timeService.saveTime(timeDto);
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
