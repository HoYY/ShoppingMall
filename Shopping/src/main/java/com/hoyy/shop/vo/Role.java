package com.hoyy.shop.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name="roles")
public class Role {
	@Id
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length=50)
	private RoleName name;

	public enum RoleName {
		ROLE_ADMIN,
		ROLE_CLIENT,
		ROLE_SELLER
	}
}
