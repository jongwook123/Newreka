package com.d103.newreka.oauth.presentation;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d103.newreka.oauth.application.OauthService;
import com.d103.newreka.oauth.domain.OauthServerType;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class OauthController {

	private final OauthService oauthService;

	@SneakyThrows
	@GetMapping("/{oauthServerType}")
	ResponseEntity<Void> redirectAuthCodeRequestUrl(
		@PathVariable OauthServerType oauthServerType,
		HttpServletResponse response
	) {
		String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);

		System.out.println(redirectUrl);
		System.out.println("=======================");
		response.sendRedirect(redirectUrl);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/login/{oauthServerType}")
	ResponseEntity<Long> login(
		@PathVariable OauthServerType oauthServerType,
		@RequestParam("code") String code
	) {
		System.out.println("======================================");
		Long login = oauthService.login(oauthServerType, code);
		System.out.println("======================================");
		return ResponseEntity.ok(login);
	}
}