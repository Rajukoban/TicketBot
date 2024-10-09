package com.server.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.server.dto.ApprovalStatusDto;
import com.server.dto.Response;
import com.server.enums.ApprovalStatus;
import com.server.model.Approval;
import com.server.model.Ticket;
import com.server.model.User;
import com.server.repository.ApprovalRepository;

@Service
public class ApprovalService {
	
	@Autowired
	private ApprovalRepository approvalRepository;
	
	@Autowired
	private TicketClientService ticketClient;
	
	@Autowired
	private UserClientServcie userClient;
	
	@Autowired
	private EmailService emailService;
	
	
	public List<Approval> getAllApprovals() {
		List<Approval> list = approvalRepository.findAll();
		ArrayList message = new ArrayList();
		message.add("Empty List");
		if (list.isEmpty()) {
			list.addAll(message);
		}
		return list;
	}

	public Approval getUserById(Long id) {
		return approvalRepository.findById(id).get();
	}
	
	public ResponseEntity<Response> sendApproval(Approval approval) {
		List<String> message = new ArrayList<String>();
		Optional<Approval> getInfo = approvalRepository.findById(approval.getApprovalId());
		
		Ticket ticket=ticketClient.getTicketById(approval.getTicket().getTicketId()).get();
		User user=userClient.getUser(approval.getApprovedBy().getUserId()).get();
		approval.setTicket(ticket);
		approval.setApprovedBy(user);
		approval.setApprovalDate(LocalDateTime.now());

		Approval approvaldata = approvalRepository.save(approval);
		if (approvaldata != null) {
			emailService.sendEmail("${spring.mail.username}", ticket.getUser().getEmail(), "Ticket Approved Status", "The Ticket is "+ approval.getApprovalStatus()+ " and Ticket Id is: "+ ticket.getTicketId() +" with ticket title is "+ticket.getTitle());
			message.add("Approval Send Successfull!");
		} else {
			message.add("Approval not Send");
		}

		return ResponseEntity.ok(new Response(message));
	}
	
	public ResponseEntity<Response> updateApproval(Long id, Approval approval) {
		List<String> message = new ArrayList<String>();
		Optional<Approval> getInfo = approvalRepository.findById(approval.getApprovalId());

		Ticket ticket=ticketClient.getTicketById(approval.getTicket().getTicketId()).orElseThrow(()->new RuntimeException("Ticket not found"));
		User user=userClient.getUser(approval.getApprovedBy().getUserId()).orElseThrow(()->new RuntimeException("User not found"));
		try {
			if (approval.getApprovalId() == null) {
				throw new IllegalArgumentException("Approval Details is not present");
			}
			if (getInfo.isEmpty()) {
				throw new Exception("Invalid Approval Id check your Approval Id");
			} else {
				approval.setApprovedBy(approval.getApprovedBy());
				approval.setApprovalStatus(approval.getApprovalStatus());
				approval.setApprovalDate(LocalDateTime.now());
				approvalRepository.save(approval);
				emailService.sendEmail("${spring.mail.username}", ticket.getUser().getEmail(), "Ticket Approved Status Updated", "The Ticket is "+ approval.getApprovalStatus()+ " with ticket title is "+ticket.getTitle());
				message.add("Approval Details updated");
			}
		} catch (IllegalArgumentException e) {
			message.add(e.getMessage());
		} catch (Exception e) {
			message.add(e.getMessage());
		}
		return ResponseEntity.ok(new Response(message));
	}

	public List<String> deleteById(Long id) {
		List<String> message = new ArrayList<String>();
		Optional<Approval> getInfo = approvalRepository.findById(id);

		try {
			if (id == 0) {
				throw new IllegalArgumentException("approval Id is not present");
			}
			if (getInfo.isEmpty()) {
				throw new Exception("Approval Details is not found");
			} else {
				approvalRepository.deleteById(id);
				message.add("Approval Details deleted");
			}
		} catch (IllegalArgumentException e) {
			message.add(e.getMessage());
		} catch (Exception e) {
			message.add(e.getMessage());
		}

		return message;
	}
	
	public List<Approval> getApprovalStatus(){
		return approvalRepository.findByApprovalStatus(ApprovalStatus.APPROVED);
	}
	
	public List<Approval> getRejectedApprovalStatus(){
		return approvalRepository.findByApprovalStatus(ApprovalStatus.REJECTED);
	}
	
//	public List<ApprovalStatusDto> getApprovalStatusData() {
//        return approvalRepository.findAll().stream()
//            .map(approval -> new ApprovalStatusDto(
//                approval.getApprovalStatus().toString(), 
//                approval.getTicket().getUser().getUserId()
//            ))
//            .collect(Collectors.toList());
//    }
	
	public List<ApprovalStatusDto> getApprovalStatusData(Long userId) {
        return approvalRepository.findByTicketUserUserId(userId).stream()
            .map(approval -> new ApprovalStatusDto(
                approval.getApprovalStatus().toString(), 
                approval.getTicket().getUser().getUserId()
            ))
            .collect(Collectors.toList());
    }
	
	
	
	

}
