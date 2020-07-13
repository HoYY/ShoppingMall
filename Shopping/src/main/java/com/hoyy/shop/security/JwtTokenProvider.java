package com.hoyy.shop.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtTokenProvider {
	@Value("${jwt.jwtSecret}")
	private String jwtSecret;
	
	@Value("${jwt.jwtExpirationInMs}")
	private int jwtExpirationInMs;
	
	@Value("${jwt.jwtTokenPrefix}")
	protected String jwtTokenPrefix;
	
	public String generateToken(Authentication authentication) {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		String token = JWT.create()
				.withSubject(userDetailsImpl.getUsername())
				.withExpiresAt(expiryDate)
				.sign(Algorithm.HMAC512(jwtSecret.getBytes()));
		
		return jwtTokenPrefix+token;
	}
	
	public String getUsernameFromJwt(String token) {
		String username = JWT.require(Algorithm.HMAC512(jwtSecret.getBytes()))
				.build()
				.verify(token.replace(jwtTokenPrefix, ""))
				.getSubject();
		return username;
	}
}
