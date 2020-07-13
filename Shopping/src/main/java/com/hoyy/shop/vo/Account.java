package com.hoyy.shop.vo;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Account {
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	private String name;
	
	private String phone;
	
	private Set<Role> authorities = new HashSet<>();
	
	public Account() {}
	
	public Account(String email, String password, String name, String phone) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
	}
	
	public void addRole(Role role) {
		authorities.add(role);
	}
}
