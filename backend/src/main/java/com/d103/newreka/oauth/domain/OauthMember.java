package com.d103.newreka.oauth.domain;

import static lombok.AccessLevel.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Table(name = "oauth_member",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "oauth_id_unique",
			columnNames = {
				"oauth_server_id",
				"oauth_server"
			}
		),
	}
)
@Getter
public class OauthMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private OauthId oauthId;

	private String nickname;

	private String accountEmail;

	public Long id() {
		return id;
	}

	public OauthId oauthId() {
		return oauthId;
	}

	public String nickname() {
		return nickname;
	}

	public String accountEmail() {
		return accountEmail;
	}
}
