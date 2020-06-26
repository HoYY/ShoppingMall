package com.hoyy.shop.controllers;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoyy.shop.dto.AccountDto;
import com.hoyy.shop.dto.LoginDto;
import com.hoyy.shop.exception.AppException;
import com.hoyy.shop.response.ApiResponse;
import com.hoyy.shop.services.AccountService;
import com.hoyy.shop.services.RoleService;
import com.hoyy.shop.vo.Account;
import com.hoyy.shop.vo.Role;
import com.hoyy.shop.vo.Role.RoleName;

@RestController
public class MainController {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping(value="/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto, 
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new ApiResponse(false, "Request Error!"), 
					HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok(new ApiResponse(true, "Login Success!"));
	}
	
	@PostMapping(value="/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid AccountDto accountDto,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity(new ApiResponse(false, "Request Error!"), 
					HttpStatus.BAD_REQUEST);
		}
		
		Account account = accountDto.toEntity();
		Role role = roleService.findOneByName(RoleName.ROLE_CLIENT)
				.orElseThrow(() -> new AppException("User role not set."));
		account.setAuthorities(Collections.singleton(role));
		
		if(!accountService.save(account)) {
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
