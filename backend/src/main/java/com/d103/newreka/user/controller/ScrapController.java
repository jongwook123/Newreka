package com.d103.newreka.user.controller;

import com.d103.newreka.jwt.util.JwtUtil;
import com.d103.newreka.security.user.UserDetailsImpl;
import com.d103.newreka.security.user.UserDetailsServiceImpl;
import com.d103.newreka.user.domain.User;
import com.d103.newreka.user.dto.ScrapDto;
import com.d103.newreka.user.dto.ScrapLoadDto;
import com.d103.newreka.user.service.ScrapService;
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
@RequestMapping("/scrap")
public class ScrapController {

    @Autowired
    private ScrapService scrapService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody ScrapDto scrapDto, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            String accessToken = request.getHeader("Authorization");
            String userEmail = jwtUtil.getEmailFromToken(accessToken);
            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(userEmail);

            User user = userDetails.getUser();
            scrapService.saveScrap(scrapDto, user);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("messege", "fail: " + e.getClass().getSimpleName());
            System.out.println(e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/scrapList")
    public ResponseEntity<Map<String, Object>> add(HttpServletRequest request) {
        //        String accessToken = request.getHeader("Authorization");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {

            String accessToken = request.getHeader("Authorization");
            String userEmail = jwtUtil.getEmailFromToken(accessToken);
            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(userEmail);

            User user = userDetails.getUser();

            List<ScrapLoadDto> scraplist = scrapService.getScrapList(user)
                    .stream()
                    .map(m -> ScrapLoadDto.fromEntity(m))
                    .collect(Collectors.toList());

            resultMap.put("scrapList", scraplist);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            //            logger.error("질문 검색 실패", e);
            resultMap.put("message", "fail: " + e.getClass().getSimpleName());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/scrapCategoryList")
    public ResponseEntity<Map<String, Object>> add2(HttpServletRequest request, @RequestParam String category) {
        //        String accessToken = request.getHeader("Authorization");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {

            String accessToken = request.getHeader("Authorization");
            String userEmail = jwtUtil.getEmailFromToken(accessToken);
            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(userEmail);

            User user = userDetails.getUser();

            List<ScrapLoadDto> scrapCategorylist = scrapService.getScrapCategoryList(user, category)
                    .stream()
                    .map(m -> ScrapLoadDto.fromEntity(m))
                    .collect(Collectors.toList());

            resultMap.put("scrapCategoryList", scrapCategorylist);
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
