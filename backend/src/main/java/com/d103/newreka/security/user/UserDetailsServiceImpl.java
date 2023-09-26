package com.d103.newreka.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.d103.newreka.user.domain.User;
import com.d103.newreka.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// userDetailsImple에 account를 넣어주는 서비스입니다.
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {

		User user = userRepository.findByNickname(nickname).orElseThrow(
			() -> new RuntimeException("Not Found Account")
		);

		UserDetailsImpl userDetails = new UserDetailsImpl();
		userDetails.setUser(user);

		return userDetails;
	}
}