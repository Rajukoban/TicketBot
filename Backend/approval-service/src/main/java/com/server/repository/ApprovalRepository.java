package com.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.server.dto.ApprovalStatusDto;
import com.server.enums.ApprovalStatus;
import com.server.model.Approval;
import com.server.model.Ticket;
import com.server.model.User;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {
	List<Approval> findByApprovalStatus(ApprovalStatus status);
	
	List<Approval> findByTicketUserUserId(Long userId);

}
