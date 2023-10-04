package com.d103.newreka.user.controller;

import com.d103.newreka.jwt.util.JwtUtil;
import com.d103.newreka.user.domain.Scrap;
import com.d103.newreka.user.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scrap")
public class ScrapController {

    private final JwtUtil jwtUtil;
    private final ScrapService scrapService;

    /** 스크랩 조회 */
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(HttpServletRequest request) {

        String accessToken = jwtUtil.getHeaderToken(request,"access");
        String userEmail = jwtUtil.getEmailFromToken(accessToken);

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            List<Scrap> scrapList = scrapService.search(userEmail);
            resultMap.put("scrapList", scrapList);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
//            logger.error("스크랩 불러오기 실패", e);
            resultMap.put("message", "fail: " + e.getClass().getSimpleName());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }





//    /** 스크랩 저장 */
//    @GetMapping("/search")
//    public ResponseEntity<Map<String, Object>> search(HttpServletRequest request) {
//        String accessToken = request.getHeader("Authorization");
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        System.out.println("1");
//        try {
//            System.out.println("111");
//            List<TeamMatchListDto> teamList = matchService.getTeamById(accessToken);
//            resultMap.put("matchList", teamList);
//            resultMap.put("message", "success");
//            status = HttpStatus.ACCEPTED;
//        } catch (Exception e) {
////            logger.error("질문 검색 실패", e);
//            resultMap.put("message", "fail: " + e.getClass().getSimpleName());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }
//
//
//
//
//    /** 스크랩 삭제 */
//    @GetMapping("/search")
//    public ResponseEntity<Map<String, Object>> search(HttpServletRequest request) {
//        String accessToken = request.getHeader("Authorization");
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        System.out.println("1");
//        try {
//            System.out.println("111");
//            List<TeamMatchListDto> teamList = matchService.getTeamById(accessToken);
//            resultMap.put("matchList", teamList);
//            resultMap.put("message", "success");
//            status = HttpStatus.ACCEPTED;
//        } catch (Exception e) {
////            logger.error("질문 검색 실패", e);
//            resultMap.put("message", "fail: " + e.getClass().getSimpleName());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }




}
