package com.d103.newreka.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d103.newreka.user.domain.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByUserEmail(String email);
}
