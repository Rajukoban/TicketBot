package com.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.server.model.Ticket;

@FeignClient("ticket-service")
public interface TicketClientService {
	
	@GetMapping("/tickets/{id}")
	public Optional<Ticket> getTicketById(@PathVariable Long id);
	
	@GetMapping
	public List<Ticket> getAllTickets();

}
