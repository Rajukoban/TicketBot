package com.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.ApprovalStatusDto;
import com.server.model.Ticket;
import com.server.services.TicketService;

@RestController
@RequestMapping("/tickets")
@CrossOrigin("*")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@PostMapping
	public Ticket createTicket(@RequestBody Ticket ticket) {
		return ticketService.createTicket(ticket);
	}
	
	@GetMapping("/{id}")
	public Optional<Ticket> getTicketById(@PathVariable Long id){
		return ticketService.getByTicketId(id);
	}
	
	@GetMapping
	public List<Ticket> getAllTickets(){
		return ticketService.getAll();	
	}
	
	@PutMapping("/{ticketId}")
	public Ticket updateTicket(@PathVariable Long ticketId,@RequestBody Ticket ticket) {
		return ticketService.updateTicket(ticketId, ticket);
	}
	
	@DeleteMapping("/{ticketId}")
	public List<String> deleteTicket(@PathVariable Long ticketId){
		return ticketService.deleteTicket(ticketId);
		
	}
	
	@GetMapping("/user/{userId}")
    public List<Ticket> getTicketsByUserId(@PathVariable Long userId) {
        return ticketService.getTicketsByUserId(userId);
    }
	
	@GetMapping("/open")
	public List<Ticket> openTickets(){
		return ticketService.openTickets();
	}
	
	@GetMapping("/pending")
	public List<Ticket> getPendingTickets() {
	    return ticketService.getPendingTickets();
	}
	
	@GetMapping("/closed")
	public List<Ticket> getClosedTickets(){
		return ticketService.getClosedTickets();
	}
	
	@GetMapping("/report")
	public Map<String, Integer> getReportData() {
	    Map<String, Integer> reportData = new HashMap<>();
	    reportData.put("totalTickets", ticketService.countAllTickets());
	    reportData.put("solvedTickets", ticketService.countSolvedTickets());
	    reportData.put("updatedTickets", ticketService.countUpdatedTickets());
	    return reportData;
	}
	
	@GetMapping("/pendings")
    public ResponseEntity<List<Ticket>> getPendingTicketsAp() {
        List<Ticket> pendingTickets = ticketService.pending();
        return ResponseEntity.ok(pendingTickets);
    }
	
	@GetMapping("/approved")
	public ResponseEntity<List<Ticket>> getApprovedTickets(@RequestParam Long userId){
		List<Ticket> tickets=ticketService.getApprovedTicketByUserId(userId);
		return ResponseEntity.ok(tickets);
	}
	
	@GetMapping("/rejected")
	public ResponseEntity<List<Ticket>> getRejectedTickets(@RequestParam Long userId){
		List<Ticket> tickets=ticketService.getRejectedTicketByUserId(userId);
		return ResponseEntity.ok(tickets);
	}
	
	@GetMapping("/approval-status")
	public ResponseEntity<List<ApprovalStatusDto>> getApprovalStatusData(){
		return ticketService.getApprovalStatusData();
	}
	
	@GetMapping("/approval-status/{userId}")
	public ResponseEntity<List<ApprovalStatusDto>> getApprovalStatusData(@PathVariable("userId") Long userId){
		return ticketService.getApprovalStatusData(userId);
	}


}
