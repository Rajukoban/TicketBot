package com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.model.EmailNotification;

public interface EmailRepository extends JpaRepository<EmailNotification, Long> {

}
