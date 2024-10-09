package com.server.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.server.dto.ApprovalStatusDto;

@FeignClient("approval-service")
public interface ApprovalClient {
	
	@GetMapping("/approval/approval-status")
    public ResponseEntity<List<ApprovalStatusDto>> getApprovalStatusData();
	
	@GetMapping("/approval/status/{userId}")
    public ResponseEntity<List<ApprovalStatusDto>> getApprovalStatusData(@PathVariable Long userId);

}
