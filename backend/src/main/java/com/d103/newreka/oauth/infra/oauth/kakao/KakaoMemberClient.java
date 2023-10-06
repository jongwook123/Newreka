package com.d103.newreka.oauth.infra.oauth.kakao;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.d103.newreka.oauth.domain.OauthMember;
import com.d103.newreka.oauth.domain.OauthServerType;
import com.d103.newreka.oauth.domain.client.OauthMemberClient;
import com.d103.newreka.oauth.infra.oauth.kakao.client.KakaoApiClient;
import com.d103.newreka.oauth.infra.oauth.kakao.dto.KakaoMemberResponse;
import com.d103.newreka.oauth.infra.oauth.kakao.dto.KakaoToken;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient {

	private final KakaoApiClient kakaoApiClient;
	private final KakaoOauthConfig kakaoOauthConfig;

	@Override
	public OauthMember fetch(String authCode) {
		KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authCode)); // (1)
		KakaoMemberResponse kakaoMemberResponse =
			kakaoApiClient.fetchMember("Bearer " + tokenInfo.getAccessToken());  // (2)
		return kakaoMemberResponse.toDomain();  // (3)
	}

	@Override
	public OauthServerType supportServer() {
		return OauthServerType.KAKAO;
	}

	private MultiValueMap<String, String> tokenRequestParams(String authCode) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", kakaoOauthConfig.getClientId());
		params.add("redirect_uri", kakaoOauthConfig.getRedirectUri());
		params.add("code", authCode);
		params.add("client_secret", kakaoOauthConfig.getClientSecret());
		return params;
	}
}