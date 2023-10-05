package com.d103.newreka.user.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d103.newreka.global.dto.GlobalResDto;
import com.d103.newreka.user.dto.LoginReqDto;
import com.d103.newreka.user.dto.UserReqDto;
import com.d103.newreka.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ResponseEntity<GlobalResDto> signup(@Valid @RequestBody UserReqDto userReqDto) {
		System.out.println(userReqDto);
		GlobalResDto response = userService.signup(userReqDto);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}

	@PostMapping("/login")
	public ResponseEntity<GlobalResDto> login(@Valid @RequestBody LoginReqDto loginReqDto,
		HttpServletResponse response) {
		System.out.println("로그인 성공");
		GlobalResDto globalResponse = userService.login(loginReqDto, response);
		return ResponseEntity.status(globalResponse.getStatusCode()).body(globalResponse);
	}
}
