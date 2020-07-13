package com.hoyy.shop.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoyy.shop.dto.LoginDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {	
	private final Logger log = LogManager.getLogger(JwtAuthenticationFilter.class);
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtTokenProvider jwtTokenProvider;
	
	private final String jwtHeaderName = "Authorization";
		
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
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(
						loginDto.getUsername(), loginDto.getPassword());
		Authentication auth = authenticationManager.authenticate(authenticationToken);
		
		return auth;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
			HttpServletResponse response, FilterChain chain, 
			Authentication authentication) throws IOException, ServletException {
		
		response.addHeader(jwtHeaderName, 
				jwtTokenProvider.generateToken(authentication));
	}
}
