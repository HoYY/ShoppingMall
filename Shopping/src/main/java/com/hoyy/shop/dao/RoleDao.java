package com.hoyy.shop.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.hoyy.shop.vo.Role;
import com.hoyy.shop.vo.Role.RoleName;

@Repository
@Mapper
public interface RoleDao {
	Set<Role> findOneByName(RoleName name);
	
	void initializeRole();
}
