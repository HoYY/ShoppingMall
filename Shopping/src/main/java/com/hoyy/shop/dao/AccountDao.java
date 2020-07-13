package com.hoyy.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hoyy.shop.vo.Account;

@Repository
@Mapper
public interface AccountDao {
	List<Map<String, String>> findJoinUserRolesByEmail(@Param("email") String email);
	
	int save(Account account) throws Exception;
}
