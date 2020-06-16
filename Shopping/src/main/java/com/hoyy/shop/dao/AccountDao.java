package com.hoyy.shop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.hoyy.shop.vo.Account;

@Repository
@Mapper
public interface AccountDao {
	Account findOneByEmail(String email) throws Exception;
}
