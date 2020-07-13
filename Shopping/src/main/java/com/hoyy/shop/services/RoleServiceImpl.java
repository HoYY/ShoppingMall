package com.hoyy.shop.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoyy.shop.dao.RoleDao;
import com.hoyy.shop.vo.Role;
import com.hoyy.shop.vo.Role.RoleName;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	
	public Set<Role> findOneByName(RoleName name) {
		return roleDao.findOneByName(name);
	}
	
	public void initializeRole() {
		roleDao.initializeRole();
	}
}
