package com.hoyy.shop.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hoyy.shop.vo.Account;

public class UserDetailsImpl implements UserDetails {
	private String username;
	
	private String password;
	
	private String name;
	
	private String phone;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsImpl(String email, String password, String name, 
			String phone, Collection<? extends GrantedAuthority> authorities) {
		this.username = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl create(Account account) {
		Set<GrantedAuthority> authorities = account.getAuthorities().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toSet());
		return new UserDetailsImpl(account.getEmail(), account.getPassword(), 
				account.getName(), account.getPhone(), authorities);
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
