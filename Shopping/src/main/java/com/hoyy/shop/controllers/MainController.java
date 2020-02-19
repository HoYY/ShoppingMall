package com.hoyy.shop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	@GetMapping
	public String mainPage() {
		return "hello";
	}
}
