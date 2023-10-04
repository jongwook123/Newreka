package com.d103.newreka.user.service;

import com.d103.newreka.global.dto.GlobalResDto;
import com.d103.newreka.jwt.dto.TokenDto;
import com.d103.newreka.jwt.util.JwtUtil;
import com.d103.newreka.user.domain.RefreshToken;
import com.d103.newreka.user.domain.Scrap;
import com.d103.newreka.user.domain.User;
import com.d103.newreka.user.dto.LoginReqDto;
import com.d103.newreka.user.dto.UserReqDto;
import com.d103.newreka.user.repo.ScrapRepo;
import com.d103.newreka.user.repository.RefreshTokenRepository;
import com.d103.newreka.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScrapService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final ScrapRepo scrapRepo;

    // 조회하기
    public List<Scrap> search(String userEmail){

        User user = userRepository.findByEmail(userEmail).orElseThrow();
        List<Scrap> list = scrapRepo.findByUser_Id(user.getId());

        return list;
    }

    @org.springframework.transaction.annotation.Transactional
    public GlobalResDto login(LoginReqDto loginReqDto, HttpServletResponse response) {

        // 아이디 검사
        User user = userRepository.findByEmail(loginReqDto.getEmail()).orElseThrow(
                () -> new RuntimeException("Not found Account")
        );

        // 비밀번호 검사
        if (!passwordEncoder.matches(loginReqDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Not matches Password");
        }

        // 아이디 정보로 Token생성
        TokenDto tokenDto = jwtUtil.createAllToken(loginReqDto.getEmail());

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUserEmail(loginReqDto.getEmail());

        if (refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        } else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginReqDto.getEmail());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);

        GlobalResDto globalResponse = new GlobalResDto("Success Login", HttpStatus.OK.value());
        globalResponse.setAccessToken(tokenDto.getAccessToken());  // AccessToken 설정

        return globalResponse;
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }
}
