package com.d103.newreka.user.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReqDto {
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	@NotBlank
	private String nickname;
	@NotBlank
	private String password;

	private String pwck; // 패스워드 확인을 위한 것

	public UserReqDto(String email, String name, String nickname, String password, String pwck) {
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.password = password;
		this.pwck = pwck;
	}

	@Override
	public String toString() {
		return "UserReqDto{" +
			"email='" + email + '\'' +
			", name='" + name + '\'' +
			", nickname='" + nickname + '\'' +
			", password='" + password + '\'' +
			", pwck='" + pwck + '\'' +
			'}';
	}

	public void setEncodePwd(String encodePwd) {
		this.password = encodePwd;
	}
}
