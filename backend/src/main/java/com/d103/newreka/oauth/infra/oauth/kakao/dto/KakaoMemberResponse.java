package com.d103.newreka.oauth.infra.oauth.kakao.dto;

import java.time.LocalDateTime;

import com.d103.newreka.oauth.domain.OauthId;
import com.d103.newreka.oauth.domain.OauthMember;
import com.d103.newreka.oauth.domain.OauthServerType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoMemberResponse {
	private Long id;
	private boolean hasSignedUp;
	private LocalDateTime connectedAt;
	private KakaoAccount kakaoAccount;

	// getters and setters...

	public OauthMember toDomain() {
		return OauthMember.builder()
			.oauthId(new OauthId(String.valueOf(id), OauthServerType.KAKAO))
			.nickname(kakaoAccount.getProfile().getNickname())
			.accountEmail(kakaoAccount.getProfile().getAccountEmail())
			.build();
	}

	@Data
	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class KakaoAccount {
		private boolean profileNeedsAgreement;
		private boolean profileNicknameNeedsAgreement;
		private boolean profileImageNeedsAgreement;
		private Profile profile;

		// Other fields...

		// getters and setters...
	}

	@Data
	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Profile {
		private String nickname;
		private String thumbnailImageUrl;
		private String accountEmail;
		// isDefaultImage field is removed because 'default' is a keyword in Java

		// getters and setters...
	}
}
