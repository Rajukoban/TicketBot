package com.server.dto;

import com.server.enums.Role;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	private String email;
	private String name;
	private Role userRole;

}
