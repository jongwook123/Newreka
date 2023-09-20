package com.d103.newreka.oauth.infra.oauth.kakao.client;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.MediaType.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.d103.newreka.oauth.infra.oauth.kakao.dto.KakaoMemberResponse;
import com.d103.newreka.oauth.infra.oauth.kakao.dto.KakaoToken;

// FeignClient 어노테이션으로 Kakao API 서버의 기본 URL을 설정합니다.
// 이렇게 하면 각 메소드에서 전체 URL을 지정하지 않아도 됩니다.
@FeignClient(name = "kakao", url = "https://kapi.kakao.com")
public interface KakaoApiClient {

	@PostMapping(value = "/oauth/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
	KakaoToken fetchToken(@RequestBody MultiValueMap<String, String> params);

	@GetMapping("/v2/user/me")
	KakaoMemberResponse fetchMember(@RequestHeader(name = AUTHORIZATION) String bearerToken);
}
