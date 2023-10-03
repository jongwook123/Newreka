package com.d103.newreka.oauth.infra.oauth.kakao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "oauth.kakao")
public class KakaoOauthConfig {
	private String redirectUri;
	private String clientId;
	private String clientSecret;
	private String[] scope;

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String[] getScope() {
		return scope.clone();
	}

	public void setScope(String[] scope) {
		this.scope = scope.clone();
	}
}
