package com.d103.newreka.global.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GlobalResDto {
	private String msg;
	private int statusCode;
	private String accessToken;  // AccessToken 필드 추가

	public GlobalResDto(String msg, int statusCode) {
		this.msg = msg;
		this.statusCode = statusCode;
	}
}
