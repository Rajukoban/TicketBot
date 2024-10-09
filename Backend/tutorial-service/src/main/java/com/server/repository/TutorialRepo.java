package com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.model.Tutorial;

public interface TutorialRepo extends JpaRepository<Tutorial, Long> {

}
