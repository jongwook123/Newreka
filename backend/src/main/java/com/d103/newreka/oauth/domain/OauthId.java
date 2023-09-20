package com.d103.newreka.oauth.domain;

import static javax.persistence.EnumType.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class OauthId {

	@Column(nullable = false, name = "oauth_server_id")
	private String oauthServerId;

	@Enumerated(STRING)
	@Column(nullable = false, name = "oauth_server")
	private OauthServerType oauthServerType;

	public String oauthServerId() {
		return oauthServerId;
	}

	public OauthServerType oauthServer() {
		return oauthServerType;
	}
}