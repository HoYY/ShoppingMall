package com.hoyy.shop.services;

import java.util.Optional;

import com.hoyy.shop.vo.Role;
import com.hoyy.shop.vo.Role.RoleName;

public interface RoleService {
	public Optional<Role> findOneByName(RoleName name);
}
