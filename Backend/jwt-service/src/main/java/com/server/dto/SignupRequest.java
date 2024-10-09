package com.server.dto;

import com.server.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class SignupRequest {
	
	private String email;
	
	private String password;
	
	private String name;

}
