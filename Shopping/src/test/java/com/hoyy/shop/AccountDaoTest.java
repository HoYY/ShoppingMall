package com.hoyy.shop;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hoyy.shop.dao.AccountDao;
import com.hoyy.shop.services.AccountService;
import com.hoyy.shop.vo.Account;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountDaoTest {
	@Autowired
	private AccountService accountServiceImpl;
	
	@Autowired
	private AccountDao accountDao;
	
	@Test
	public void SaveAndFindTest() throws Exception {
		final Account account = new Account("t", "t", "t", "111-111-1111");
		accountDao.save(account);
		final Account selectAccount = accountServiceImpl.findOneByEmail("t");
		assertThat(selectAccount.getEmail(), is("t"));
	}
}
