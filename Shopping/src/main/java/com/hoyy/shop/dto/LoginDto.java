package com.hoyy.shop.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	@NotBlank(message="값을 입력해주세요.")
	private String id;
	
	@Size(max=50, message="암호는 최대 50자까지 허용됩니다.")
	@Pattern(regexp="[^ㅁ-ㅎ가-힣]*", message="한글은 사용할 수 없습니다.")
	private String password;
}
