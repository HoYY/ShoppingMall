package com.hoyy.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hoyy.shop.services.AccountServiceImpl;
import com.hoyy.shop.vo.Account;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	@Autowired
	private UserDetailsImpl userDetailsImpl;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountServiceImpl.findOneByEmail(email);
		return userDetailsImpl.create(account);
	}
}