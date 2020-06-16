package com.hoyy.shop.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hoyy.shop.dao.AccountDao;
import com.hoyy.shop.vo.Account;

public class AccountServiceImpl implements AccountService {
	private static final Logger log = LogManager.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private AccountDao accountDao;
	
	public Account findOneByEmail(String email) {
		try {
			return accountDao.findOneByEmail(email);
		}
		catch(Exception e) {
			log.error(e);
			return null;
		}
	}
}
