package com.d103.newreka.user.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginReqDto {
	@NotBlank
	private String password;
	@NotBlank
	private String email;
}


