package com.d103.newreka.oauth.domain.client;

import com.d103.newreka.oauth.domain.OauthMember;
import com.d103.newreka.oauth.domain.OauthServerType;

public interface OauthMemberClient {
	OauthMember fetch(String authCode);

	OauthServerType supportServer();
}

