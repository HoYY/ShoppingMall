package com.hoyy.shop.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hoyy.shop.vo.Role;
import com.hoyy.shop.vo.Role.RoleName;

@Repository
@Mapper
public interface UserRoleDao {
	void addRole(@Param("email") String email,
			@Param("role_name") RoleName role_name);
	
	Set<Role> findByUserEmail(String email);
}
