package com.hoyy.shop.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoyy.shop.dto.LoginDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {	
	private final Logger log = LogManager.getLogger(JwtAuthenticationFilter.class);
	
	private final AuthenticationManager authenticationManager;
	
	@Value("${jwt.jwtSecret}")
	private String jwtSecret;
	
	@Value("${jwt.jwtExpirationInMs}")
	private int jwtExpirationInMs;
	
	@Value("${jwt.jwtHeaderName}")
	private String jwtHeaderName;
	
	@Value("${jwt.jwtTokenPrefix}")
	private String jwtTokenPrefix;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
			HttpServletResponse response) throws AuthenticationException {
		LoginDto loginDto = null;
		try {
			loginDto = new ObjectMapper()
					.readValue(request.getInputStream(), LoginDto.class);
		}
		catch(IOException e) {
			log.error(e);
		}
		
		UsernamePasswordAuthenticationToken authenticaitonToken = 
				new UsernamePasswordAuthenticationToken(
						loginDto.getUsername(), loginDto.getPassword());
		Authentication auth = authenticationManager.authenticate(authenticaitonToken);
		
		return auth;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
			HttpServletResponse response, FilterChain chain, 
			Authentication authentication) throws IOException, ServletException {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
		
		String token = JWT.create()
				.withSubject(userDetailsImpl.getUsername())
				.withExpiresAt(expiryDate)
				.sign(Algorithm.HMAC512(jwtSecret));
		
		response.addHeader(jwtHeaderName, jwtTokenPrefix + " " + token);
	}
}
