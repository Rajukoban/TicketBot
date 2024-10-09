package com.server.service;

import java.util.List;

import com.server.dto.SignupRequest;
import com.server.dto.SignupRequestUser;
import com.server.dto.UserDto;
import com.server.model.User;

public interface UserServiceDao {
	
	//UserDto createUser(SignupRequest signupRequest);
	
	Boolean hasUserWithEmail(String email);
	
	List<User> getAllUsers();
	
	UserDto createUser1(SignupRequestUser signupRequest);

}
