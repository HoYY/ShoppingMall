package com.hoyy.shop.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoyy.shop.dto.LoginDto;
import com.hoyy.shop.response.ApiResponse;
import com.hoyy.shop.services.LoginService;

@RestController
public class MainController {
	@Autowired
	private LoginService loginService;
	
	@PostMapping(value="/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto 
			, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new ApiResponse(false, "Login Fail!"), HttpStatus.BAD_REQUEST);
		}
		if(!loginService.insertLogin(loginDto)) {
			return new ResponseEntity(new ApiResponse(false, "Login Fail!"), HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok("success");
	}
	
	@GetMapping(value="/")
	public String main() {
		return "gg";
	}
}
