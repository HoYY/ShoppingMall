package com.hoyy.shop.services;

import java.util.Set;

import com.hoyy.shop.vo.Role;
import com.hoyy.shop.vo.Role.RoleName;

public interface UserRoleService {
	public void addRole(String email, RoleName role_name) throws Exception;
	
	public Set<Role> findByUserEmail(String email);
}
