package com.d103.newreka.oauth.domain.client;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.d103.newreka.oauth.domain.OauthMember;
import com.d103.newreka.oauth.domain.OauthServerType;

@Component
public class OauthMemberClientComposite {

	private final Map<OauthServerType, OauthMemberClient> mapping;

	public OauthMemberClientComposite(Set<OauthMemberClient> clients) {
		mapping = clients.stream()
			.collect(toMap(
				OauthMemberClient::supportServer,
				identity()
			));
	}

	public OauthMember fetch(OauthServerType oauthServerType, String authCode) {
		return getClient(oauthServerType).fetch(authCode);
	}

	private OauthMemberClient getClient(OauthServerType oauthServerType) {
		return Optional.ofNullable(mapping.get(oauthServerType))
			.orElseThrow(() -> new RuntimeException("지원하지 않는 소셜 로그인 타입입니다."));
	}
}