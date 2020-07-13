package com.hoyy.shop.vo;

import lombok.Data;

@Data
public class Role {
	private RoleName name;
	
	public Role(String role) {
		name = RoleName.valueOf(role);
	}
	
	public enum RoleName {
		ROLE_ADMIN,
		ROLE_CLIENT,
		ROLE_SELLER
	}
}
