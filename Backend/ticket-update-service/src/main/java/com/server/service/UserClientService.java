package com.server.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.server.model.User;

@FeignClient("jwt-service")
public interface UserClientService {
	
	@GetMapping("/jwt/hii/{id}")
	public Optional<User> getUser(@PathVariable Long id);

}
