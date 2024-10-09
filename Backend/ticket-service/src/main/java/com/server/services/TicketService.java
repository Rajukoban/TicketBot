package com.server.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.server.dto.ApprovalStatusDto;
import com.server.enums.TicketStatus;
import com.server.model.Ticket;
import com.server.model.User;
import com.server.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private UserClientService userClient;
	
	@Autowired
    private ApprovalClient approvalClient;

	@Autowired
	private EmailService emailService;

	public Ticket createTicket(Ticket ticket) {
		ticket.setCreatedDate(LocalDateTime.now());
		User user = userClient.getUser(ticket.getUser().getUserId()).get();
		ticket.setUser(user);
		Ticket ticket1 = ticketRepository.save(ticket);
		emailService.sendEmail("${spring.mail.username}", user.getEmail(), "New Ticket Created",
				"The New Ticket has been Created. The Ticket Titile is: " + ticket.getTitle());
		return ticket1;
	}

	public Optional<Ticket> getByTicketId(Long id) {
		return ticketRepository.findById(id);
	}

	public List<Ticket> getAll() {
		return ticketRepository.findAll();
	}

	public Ticket updateTicket(Long ticketId, Ticket ticket) {
		Ticket ticketDetails = ticketRepository.findById(ticketId).get();
		User user = userClient.getUser(ticket.getUser().getUserId()).get();
		ticketDetails.setTitle(ticket.getTitle());
		ticketDetails.setDescription(ticket.getDescription());
		ticketDetails.setStatus(ticket.getStatus());
		ticketDetails.setPriority(ticket.getPriority());
		ticketDetails.setUser(ticket.getUser());
		Ticket ticketUpdate = ticketRepository.save(ticketDetails);
		emailService.sendEmail("${spring.mail.username}", user.getEmail(), "Ticket Updated",
				"The Ticket has been updated. The Ticket Titile is: " + ticket.getTitle());
		return ticketUpdate;

	}

	public List<String> deleteTicket(Long ticketId) {
		List<String> message = new ArrayList<String>();
		Optional<Ticket> getInfo = ticketRepository.findById(ticketId);
		Ticket ticket = ticketRepository.findById(ticketId).get();
		// User user=userClient.getByUserId(ticket.getUser().getUserId()).get();
		User user = userClient.getUser(ticket.getUser().getUserId()).get();
		try {
			if (ticketId == 0) {
				throw new IllegalArgumentException("Ticket Id not Found");
			}
			if (getInfo.isEmpty()) {
				throw new Exception("Invalid Ticket Id Number");
			} else {
				ticketRepository.deleteById(ticketId);
				emailService.sendEmail("${spring.mail.username}", user.getEmail(), "Ticket Deleted",
						"Ticket has been deleted. ticket title is: " + ticket.getTitle());
				message.add("Ticket Deleted Successfull");
			}
		} catch (IllegalArgumentException e) {
			message.add(e.getMessage());
		} catch (Exception e) {
			message.add(e.getMessage());
		}

		return message;

	}
	
	public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findTicketsByUserId(userId);
    }
	
	public List<Ticket> openTickets(){
		return ticketRepository.findByStatus(TicketStatus.OPEN);
	}
	
	public List<Ticket> getPendingTickets() {
	    return ticketRepository.findAll().stream()
	        .filter(ticket -> ticket.getStatus() == TicketStatus.OPEN || ticket.getStatus() == TicketStatus.IN_PROGRESS)
	        .collect(Collectors.toList());
	}
	
	public List<Ticket> getClosedTickets(){
		return ticketRepository.findByStatus(TicketStatus.CLOSED);
	}
	
	public int countAllTickets() {
	    return (int) ticketRepository.count();
	}

	public int countSolvedTickets() {
	    return (int) ticketRepository.countByStatus(TicketStatus.RESOLVED);
	}

	public int countUpdatedTickets() {
	    return ticketRepository.countUpdatedTickets();
	}
	
	public List<Ticket> pending() {
        return ticketRepository.findPendingTickets();
    }
	
	public List<Ticket> getApprovedTicketByUserId(Long userId){
		return ticketRepository.findApprovedTicketByUserId(userId);
	}
	
	public List<Ticket> getRejectedTicketByUserId(Long userId){
		return ticketRepository.findRejectedTicketByUserId(userId);
	}

	
	//Bar Graph services
	
	public ResponseEntity<List<ApprovalStatusDto>> getApprovalStatusData() {
        return approvalClient.getApprovalStatusData();
    }
	
	public ResponseEntity<List<ApprovalStatusDto>> getApprovalStatusData(Long userId){
		return approvalClient.getApprovalStatusData(userId);
	}
}
