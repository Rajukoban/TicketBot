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
import com.server.model.TicketUpdate;
import com.server.service.TicketUpdateService;

@RestController
@RequestMapping("ticketupdates")
public class TicketUpdateController {
	
	@Autowired
	private TicketUpdateService ticketUpdateService;
	
	@GetMapping
	public List<TicketUpdate> getAllTicketUpdates(){
		return ticketUpdateService.getAllTicketsUpdates();
	}
	
	@GetMapping("/{updateId}")
	public ResponseEntity<TicketUpdate> getTicketUpdateById(@PathVariable Long updateId){
		Optional<TicketUpdate> ticketUpdate=ticketUpdateService.getTicketUpdateById(updateId);
		return ticketUpdate.map(ResponseEntity::ok)
				.orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Response> createTicketUpdate(@RequestBody TicketUpdate ticketUpdate) {
		return ticketUpdateService.createTicketUpdate(ticketUpdate);
	}
	
	@PutMapping("/{updateId}")
	public ResponseEntity<Response> updateTicketUpdate(@PathVariable Long updateId,@RequestBody TicketUpdate updateDetails){
		return ticketUpdateService.updateTicketUpdate(updateId,updateDetails);
	}
	
	@DeleteMapping("/{updateId}")
	public List<String> deleteTicketUpdate(@PathVariable Long updateId) {
		return ticketUpdateService.deleteTicketUpdate(updateId);
		
	}

}
