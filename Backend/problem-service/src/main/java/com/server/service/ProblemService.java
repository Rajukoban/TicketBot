package com.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.server.dto.Response;
import com.server.model.Problem;
import com.server.repository.ProblemRepository;

@Service
public class ProblemService {
	
	@Autowired
	private ProblemRepository problemRepository;

	public List<Problem> getAllProblems() {
		List<Problem> list=problemRepository.findAll();
		ArrayList message=new ArrayList();
		message.add("Empty List");
		if(list.isEmpty()) {
			list.addAll(message);
		}
		return list;
	}

	public Optional<Problem> getProblemById(Long problemId) {
		return problemRepository.findById(problemId);
	}
	
	public ResponseEntity<Response> createProblem(Problem problem) {
		List<String> message=new ArrayList<String>();
		Optional<Problem> getInfo=problemRepository.findById(problem.getProblemId());
		Problem problemp=problemRepository.save(problem);
		
		if(problemp!=null) {
			message.add("Problem Created Successfull");
		}else {
			message.add("Problem Not Created...");
		}	
		return ResponseEntity.ok(new Response(message));

		}
		
		
	
	public ResponseEntity<Response> updateProblem(Long problemId, Problem problemDetails) {
		List<String> message=new ArrayList<String>();
    	Optional<Problem> getInfo=problemRepository.findById(problemDetails.getProblemId());
    	
    	try {
    		if(problemDetails.getProblemId()==null) {
    			throw new IllegalArgumentException("Problem Details is not present");
    		}
    		if(getInfo.isEmpty()) {
    			throw new Exception("Invalid Problem Id check your email Id");
    		}else {
    			problemDetails.setProblemId(problemId);
    			problemRepository.save(problemDetails);
    			message.add("Problem Details updated");
    		}
    	}catch(IllegalArgumentException e) {
    		message.add(e.getMessage());
    	}catch(Exception e) {
    		message.add(e.getMessage());
    	}
    	return ResponseEntity.ok(new Response(message));
    }
	
	public List<String> deleteProblem(Long problemId) {
		List<String> message=new ArrayList<String>();
    	Optional<Problem> getInfo=problemRepository.findById(problemId);
    	
    	try {
    		if(problemId==0) {
    			throw new IllegalArgumentException("Problem Id is not present");
    		}
    		if(getInfo.isEmpty()) {
    			throw new Exception("Problem Details is not found");
    		}else {
    			problemRepository.deleteById(problemId);
    			message.add("Problem Details deleted");
    		}
    	}catch(IllegalArgumentException e) {
    		message.add(e.getMessage());
    	}catch(Exception e) {
    		message.add(e.getMessage());
    	}
    	
    	return message;
	}

}
