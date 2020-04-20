package com.hoyy.shop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.hoyy.shop.dto.LoginDto;

@Repository
@Mapper
public interface LoginDao {
	void insertLogin(LoginDto loginDto) throws Exception;
}
