package com.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.enums.Role;
import com.server.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findFirstByEmail(String email);
	
	List<User> findByRole(Role userRole);
	
	Optional<User> findByEmail(String email);

}
