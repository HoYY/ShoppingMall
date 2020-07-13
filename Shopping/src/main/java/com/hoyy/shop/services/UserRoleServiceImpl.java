package com.hoyy.shop.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoyy.shop.dao.UserRoleDao;
import com.hoyy.shop.vo.Role;
import com.hoyy.shop.vo.Role.RoleName;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDao userRoleDao;
	
	public void addRole(String email, RoleName role_name) throws Exception {
		userRoleDao.addRole(email, role_name);
	}
	
	public Set<Role> findByUserEmail(String email) {
		return userRoleDao.findByUserEmail(email);
	}
}
