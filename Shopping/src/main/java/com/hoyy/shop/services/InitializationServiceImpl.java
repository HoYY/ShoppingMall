package com.hoyy.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoyy.shop.dao.InitializationDao;

@Service
public class InitializationServiceImpl implements InitializationService {
	@Autowired
	private InitializationDao initializationDao;
	
	public void createTable() {
		String valueUsers = "create table if not exists users("
				+ "email varchar(100) primary key,"
				+ "password varchar(70) not null,"
				+ "name varchar(30) not null,"
				+ "phone varchar(13) not null)";
		initializationDao.createTable(valueUsers);
		
		String valueRoles = "create table if not exists roles("
				+ "name enum('ROLE_ADMIN','ROLE_CLIENT','ROLE_SELLER') primary key)";
		initializationDao.createTable(valueRoles);
		
		String valueUserRoles = "create table if not exists user_roles("
				+ "user_email varchar(100) not null,"
				+ "role_name enum('ROLE_ADMIN','ROLE_CLIENT','ROLE_SELLER') not null,"
				+ "primary key(user_email, role_name),"
				+ "foreign key(user_email) references users(email) "
				+ "on update cascade on delete cascade,"
				+ "foreign key(role_name) references roles(name) "
				+ "on update cascade)";
		initializationDao.createTable(valueUserRoles);
	}
}
