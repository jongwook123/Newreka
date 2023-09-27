package com.d103.newreka.user.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d103.newreka.global.dto.GlobalResDto;
import com.d103.newreka.jwt.dto.TokenDto;
import com.d103.newreka.jwt.util.JwtUtil;
import com.d103.newreka.user.domain.RefreshToken;
import com.d103.newreka.user.domain.User;
import com.d103.newreka.user.dto.LoginReqDto;
import com.d103.newreka.user.dto.UserReqDto;
import com.d103.newreka.user.repository.RefreshTokenRepository;
import com.d103.newreka.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final RefreshTokenRepository refreshTokenRepository;

	@Transactional
	public GlobalResDto signup(UserReqDto userReqDto) {
		// nickname 중복검사
		if (userRepository.findByEmail(userReqDto.getNickname()).isPresent()) {
			throw new RuntimeException("Overlap Check");
		}

		// 패스워드 암호화
		userReqDto.setEncodePwd(passwordEncoder.encode(userReqDto.getPassword()));

		User user = new User(userReqDto);
		System.out.println(user.getPassword());
		// 회원가입 성공
		userRepository.save(user);

		return new GlobalResDto("Success signup", HttpStatus.OK.value());
	}

	@Transactional
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

		return new GlobalResDto("Success Login", HttpStatus.OK.value());
	}

	private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
		response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
		response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
	}
}

