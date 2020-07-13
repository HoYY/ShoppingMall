package com.hoyy.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.hoyy.shop.services.InitializationService;
import com.hoyy.shop.services.RoleService;

@SpringBootApplication
public class ShoppingApplication {
	@Autowired
	private RoleService roleServiceImpl;
	
	@Autowired
	private InitializationService initializationServiceImpl;
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
    public void initializeRole() {
		initializationServiceImpl.createTable();
    	roleServiceImpl.initializeRole();
    }
}
