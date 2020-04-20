package com.hoyy.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoyy.shop.dao.LoginDao;
import com.hoyy.shop.dto.LoginDto;

@Service
public class LoginService {
	@Autowired
	private LoginDao loginDao;
	
	public boolean insertLogin(LoginDto loginDto) {
		try {
			loginDao.insertLogin(loginDto);
			return true;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
