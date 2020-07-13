package com.hoyy.shop.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableTransactionManagement
public class DatabaseConfig {
	private static final Logger log = LogManager.getLogger(DatabaseConfig.class);
	/*
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		log.info("dataSource : {}", dataSource);
		return dataSource;
	}*/
	
	@Autowired
	private DataSource dataSource;
	
	/*@Bean
	public PlatformTransactionManager transactionManager() 
			throws URISyntaxException, GeneralSecurityException, 
			ParseException, IOException { 
		log.info("dataSource : {}", dataSource);
		return new DataSourceTransactionManager(dataSource); 
	}*/
}
