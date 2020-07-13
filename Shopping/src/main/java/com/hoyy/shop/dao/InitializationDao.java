package com.hoyy.shop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface InitializationDao {
	void createTable(String value);
}
