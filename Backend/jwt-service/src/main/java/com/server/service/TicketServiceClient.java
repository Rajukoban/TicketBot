package com.server.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ticket-service")
public interface TicketServiceClient {

	@DeleteMapping("/tickets/{ticketId}")
	public List<String> deleteTicket(@PathVariable Long ticketId);
}
