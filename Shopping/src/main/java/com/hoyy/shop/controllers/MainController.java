package com.hoyy.shop.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoyy.shop.dto.AccountDto;
import com.hoyy.shop.response.ApiResponse;
import com.hoyy.shop.services.AccountService;

@RestController
public class MainController {
	private static final Logger log = LogManager.getLogger(MainController.class);
	
	@Autowired
	private AccountService accountServiceImpl;
	
	@PostMapping(value="/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid AccountDto accountDto,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new ApiResponse(false, "Request Error!"), 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			accountServiceImpl.save(accountDto);
		}
		catch(Exception e) {
			log.error(e);
			return new ResponseEntity(new ApiResponse(false, "Sign Up Fail!"), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(new ApiResponse(true, "Sign Up Success!"));
	}

	@GetMapping(value="/")
	public String main() {
		return "gg";
	}
}
