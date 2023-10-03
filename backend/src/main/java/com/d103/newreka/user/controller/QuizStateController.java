package com.d103.newreka.user.controller;

import com.d103.newreka.quiz.service.QuizService;
import com.d103.newreka.user.service.QuizStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quizState")
public class QuizStateController {

    @Autowired
    private QuizStateService quizStateService;



}
