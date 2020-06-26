package com.hoyy.shop.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoyy.shop.dao.AccountDao;
import com.hoyy.shop.vo.Account;

@Service
public class AccountServiceImpl implements AccountService {	
	private final Logger log = LogManager.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Account findOneByEmail(String email) {
		return accountDao.findOneByEmail(email);
	}
	
	public boolean save(Account account) {
		try {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			accountDao.save(account);
			return true;
		}
		catch(Exception e) {
			log.error(e);
			return false;
		}
	}
}
