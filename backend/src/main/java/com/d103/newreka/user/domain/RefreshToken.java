package com.d103.newreka.user.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String refreshToken;
	@NotBlank
	private String userEmail;

	public RefreshToken(String token, String email) {
		this.refreshToken = token;
		this.userEmail = email;
	}

	public RefreshToken updateToken(String token) {
		this.refreshToken = token;
		return this;
	}
}
