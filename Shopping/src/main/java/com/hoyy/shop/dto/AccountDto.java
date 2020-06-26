package com.hoyy.shop.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.hoyy.shop.vo.Account;

import lombok.Data;

@Data
public class AccountDto {
	@NotBlank(message="공백을 허용하지 않습니다.")
	@Size(max=100, message="이메일은 최대 100자까지 허용됩니다.")
	@Pattern(regexp="[^ㅁ-ㅎ가-힣]*", message="이메일에 한글은 사용할 수 없습니다.")
	private String email;
	
	@NotBlank(message="공백을 허용하지 않습니다.")
	@Size(max=70, message="비밀번호는 최대 70자까지 허용됩니다.")
	@Pattern(regexp="[^ㅁ-ㅎ가-힣]*", message="비밀번호에 한글은 사용할 수 없습니다.")
	private String password;
	
	@NotEmpty(message="값을 입력해주세요.")
	@Size(max=30, message="이름은 최대 30자까지 허용됩니다.")
	@Pattern(regexp="[a-zA-Z0-9ㅁ-ㅎ가-힣]*", message="이름에는 특수문자를 사용할 수 없습니다.")
	private String name;
	
	@NotBlank(message="공백을 허용하지 않습니다.")
	@Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$", message="핸드폰 포맷을 지켜주세요.")
	private String phone;
	
	public Account toEntity() {
		return (new Account(email, password, name, phone));
	}
}
