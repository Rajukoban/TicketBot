package com.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.ApprovalStatusDto;
import com.server.dto.Response;
import com.server.enums.ApprovalStatus;
import com.server.model.Approval;
import com.server.model.Ticket;
import com.server.model.User;
import com.server.service.ApprovalService;

@RestController
@RequestMapping("/approval")
@CrossOrigin("*")
public class ApprovalController {
	
	@Autowired
	private ApprovalService approvalService;
	
	@GetMapping
	public List<Approval> getAll(){
		return approvalService.getAllApprovals();
	}
	
	@GetMapping("/getApprovalId/{id}")
	public Approval getByUserId(@PathVariable("id") Long id) {
		return approvalService.getUserById(id);
	}
	
	@PostMapping("/sendApproval")
	public ResponseEntity<Response> sendApproval(@RequestBody Approval approval){
		return approvalService.sendApproval(approval);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Response> updateApprovalById(@RequestBody Approval approval,@PathVariable Long id) {
		return approvalService.updateApproval(id, approval);
	}
	
	@DeleteMapping("/delete/{id}")
	public List<String> deleteById(@PathVariable Long id){
		return approvalService.deleteById(id);
	}
	
	@GetMapping("/status")
	public List<Approval> getApprovalStatus(){
		return approvalService.getApprovalStatus();
	}
	
	@GetMapping("/reject")
	public List<Approval> getRejectApprovalStatus(){
		return approvalService.getRejectedApprovalStatus();
	}
	
//	@GetMapping("/approval-status")
//    public ResponseEntity<List<ApprovalStatusDto>> getApprovalStatusData() {
//        List<ApprovalStatusDto> approvalStatusData = approvalService.getApprovalStatusData();
//        return ResponseEntity.ok(approvalStatusData);
//    }
	
	@GetMapping("/status/{userId}")
    public ResponseEntity<List<ApprovalStatusDto>> getApprovalStatusData(@PathVariable Long userId) {
        List<ApprovalStatusDto> approvalStatusData = approvalService.getApprovalStatusData(userId);
        return ResponseEntity.ok(approvalStatusData);
    }
	
}
