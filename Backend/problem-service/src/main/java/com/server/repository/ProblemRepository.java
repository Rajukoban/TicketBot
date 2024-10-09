package com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.model.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

}
