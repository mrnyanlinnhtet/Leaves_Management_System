package com.jdc.leaves.model.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDao {
	
	private int id;
	private String name;
	private String role;
	private String email;
	private String password;
	private boolean deleted;

}
