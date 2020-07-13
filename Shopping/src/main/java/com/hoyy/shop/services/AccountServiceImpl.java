package com.hoyy.shop.services;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoyy.shop.dao.AccountDao;
import com.hoyy.shop.dto.AccountDto;
import com.hoyy.shop.vo.Account;
import com.hoyy.shop.vo.Role;
import com.hoyy.shop.vo.Role.RoleName;

@Service
public class AccountServiceImpl implements AccountService {	
	private static final Logger log = LogManager.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private UserRoleService userRoleServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Account findOneByEmail(String email) {
		List<Map<String, String>> list = accountDao.findJoinUserRolesByEmail(email);
		Account account = new Account(list.get(0).get("email"), list.get(0).get("password"), 
				list.get(0).get("name"), list.get(0).get("phone"));
		for(Map<String, String> m: list) {
			account.addRole(new Role(m.get("authorities")));
		}
		
		return account;
	}
	
	public void save(AccountDto accountDto) {
		try {
			Account account = accountDto.toEntity();
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			accountDao.save(account);
			userRoleServiceImpl.addRole(account.getEmail(), 
					RoleName.ROLE_CLIENT);
		}
		catch(Exception e) {
			log.error(e);
		}
	}
}
