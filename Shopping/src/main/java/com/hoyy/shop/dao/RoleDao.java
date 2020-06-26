package com.hoyy.shop.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.hoyy.shop.vo.Role;
import com.hoyy.shop.vo.Role.RoleName;

@Repository
@Mapper
public interface RoleDao {
	Optional<Role> findOneByName(RoleName name);
}
