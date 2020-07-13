package com.hoyy.shop.services;

import java.util.Set;

import com.hoyy.shop.vo.Role;
import com.hoyy.shop.vo.Role.RoleName;


public interface RoleService {
	public Set<Role> findOneByName(RoleName name);
	
	public void initializeRole();
}
