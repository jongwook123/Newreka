package com.d103.newreka.user.controller;

import com.d103.newreka.jwt.util.JwtUtil;
import com.d103.newreka.quiz.service.QuizService;
import com.d103.newreka.security.user.UserDetailsImpl;
import com.d103.newreka.security.user.UserDetailsServiceImpl;
import com.d103.newreka.user.domain.User;
import com.d103.newreka.user.service.QuizStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quizState")
public class QuizStateController {

    @Autowired
    private QuizStateService quizStateService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/myKeyWord")
    public ResponseEntity<Map<String, Object>> myKeyWord(HttpServletRequest request){

        Map<String, Object> resultMap = new HashMap<>();

        HttpStatus status = null;
        try {
            String accessToken = request.getHeader("Authorization");
            String userEmail = jwtUtil.getEmailFromToken(accessToken);
            UserDetailsImpl userDetails = (UserDetailsImpl)userDetailsService.loadUserByUsername(userEmail);

            User user = userDetails.getUser();

            Map<String,Integer> myKeyWord = quizStateService.myKeyWord(user);

            resultMap.put("message", "success");
            resultMap.put("keyWordList", myKeyWord);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
            System.out.println(e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
