package com.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.Response;
import com.server.model.Problem;
import com.server.service.ProblemService;

@RestController
@RequestMapping("/problems")
public class ProblemController {
	
	@Autowired
	private ProblemService problemService;
	
	@GetMapping
	public List<Problem> getAllProblems(){
		return problemService.getAllProblems();
	}
	
	@GetMapping("/{problemId}")
	public Optional<Problem> getProblemById(@PathVariable Long problemId){
		Optional<Problem> problem=problemService.getProblemById(problemId);
		return problem;
	}
	
	@PostMapping
	public ResponseEntity<Response> createProblem(@RequestBody Problem problem) {
		return problemService.createProblem(problem);
	}
	
	@PutMapping("/{problemId}")
	public ResponseEntity<Response> updateProblem(@PathVariable Long problemId, @RequestBody Problem problemDetails){
		return problemService.updateProblem(problemId, problemDetails);
	}
	
	@DeleteMapping("/{problemId}")
	public List<String> deleteProblem(@PathVariable Long problemId){
		return problemService.deleteProblem(problemId);
	}

}
