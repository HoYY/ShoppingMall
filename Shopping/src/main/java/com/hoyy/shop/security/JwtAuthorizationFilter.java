package com.hoyy.shop.security;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.hoyy.shop.services.AccountService;
import com.hoyy.shop.vo.Account;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {	
	private final JwtTokenProvider jwtTokenProvider;
	
	private final AccountService accountServiceImpl;
	
	private final String jwtHeaderName = "Authorization";
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, 
			JwtTokenProvider jwtTokenProvider, AccountService accountServiceImpl) {
		super(authenticationManager);
		this.jwtTokenProvider = jwtTokenProvider;
		this.accountServiceImpl = accountServiceImpl;
	}
	
	private Authentication getUsernamePasswordAuthentication(
			HttpServletRequest request) {
		String token = request.getHeader(jwtHeaderName);
		String username = jwtTokenProvider.getUsernameFromJwt(token);
		if(username != null) {
			Account account = accountServiceImpl.findOneByEmail(username);
			UsernamePasswordAuthenticationToken auth = 
					new UsernamePasswordAuthenticationToken(
							username, null, 
							account.getAuthorities().stream()
							.map(role -> new SimpleGrantedAuthority(
									role.getName().name()))
							.collect(Collectors.toSet()));
			return auth;
		}
		return null;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String token = request.getHeader(jwtHeaderName);
		if(token == null || token.startsWith(jwtTokenProvider.jwtTokenPrefix)) {
			chain.doFilter(request, response);
			return;
		}
		System.out.println(token);
		Authentication auth = getUsernamePasswordAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		chain.doFilter(request, response);
	}
}
