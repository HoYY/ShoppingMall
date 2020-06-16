package com.hoyy.shop.services;

import com.hoyy.shop.vo.Account;

public interface AccountService {
	public Account findOneByEmail(String email);
}
