package com.d103.newreka.oauth.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d103.newreka.oauth.domain.OauthMember;
import com.d103.newreka.oauth.domain.OauthMemberRepository;
import com.d103.newreka.oauth.domain.OauthServerType;
import com.d103.newreka.oauth.domain.authcode.AuthCodeRequestUrlProviderComposite;
import com.d103.newreka.oauth.domain.client.OauthMemberClientComposite;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OauthService {

	private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
	private final OauthMemberClientComposite oauthMemberClientComposite;
	private final OauthMemberRepository oauthMemberRepository;

	@Transactional
	public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
		return authCodeRequestUrlProviderComposite.provide(oauthServerType);
	}

	@Transactional
	public Long login(OauthServerType oauthServerType, String authCode) {
		OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
		OauthMember saved = oauthMemberRepository.findByOauthId(oauthMember.oauthId())
			.orElseGet(() -> oauthMemberRepository.save(oauthMember));
		return saved.id();
	}
}
