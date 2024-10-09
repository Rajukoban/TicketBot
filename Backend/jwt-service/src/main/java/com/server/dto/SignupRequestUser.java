package com.server.dto;

import com.server.enums.Role;

import lombok.Data;

@Data
public class SignupRequestUser {
	private String email;
	
	private String password;
	
	private String name;
	
	private Role role;

}
