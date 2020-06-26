package com.hoyy.shop.vo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class Account {
	@Id
	@NotNull
	@Column(length=100)
	private String email;
	
	@NotNull
	@Column(length=70)
	private String password;
	
	@Column(length=30)
	private String name;
	
	@Column(length=13)
	private String phone;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="user_roles", joinColumns= {@JoinColumn(name="email")},
			inverseJoinColumns = {@JoinColumn(name="role_name")})
	private Set<Role> authorities = new HashSet<>();
	
	public Account() {}
	
	public Account(String email, String password, String name, String phone) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
	}
}
