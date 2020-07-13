package com.hoyy.shop.services;

import com.hoyy.shop.dto.AccountDto;
import com.hoyy.shop.vo.Account;

public interface AccountService {
	public Account findOneByEmail(String email);
	
	public void save(AccountDto accountDto);
}
