package com.d103.newreka.oauth.infra.oauth.kakao.authcode;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.d103.newreka.oauth.domain.OauthServerType;
import com.d103.newreka.oauth.domain.authcode.AuthCodeRequestUrlProvider;
import com.d103.newreka.oauth.infra.oauth.kakao.KakaoOauthConfig;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

	private final KakaoOauthConfig kakaoOauthConfig;

	@Override
	public OauthServerType supportServer() {
		return OauthServerType.KAKAO;
	}

	@Override
	public String provide() {
		return UriComponentsBuilder
			.fromUriString("https://kauth.kakao.com/oauth/authorize")
			.queryParam("response_type", "code")
			.queryParam("client_id", kakaoOauthConfig.getClientId())
			.queryParam("redirect_uri", kakaoOauthConfig.getRedirectUri())
			.queryParam("scope", String.join(",", kakaoOauthConfig.getScope()))
			.toUriString();
	}
}
