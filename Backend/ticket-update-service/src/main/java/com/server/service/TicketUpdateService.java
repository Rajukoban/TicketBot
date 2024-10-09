package com.server.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.server.dto.Response;
import com.server.model.Ticket;
import com.server.model.TicketUpdate;
import com.server.model.User;
import com.server.repository.TicketUpdateRepository;

@Service
public class TicketUpdateService {
	
	@Autowired
	private TicketUpdateRepository ticketUpdateRepository;
	
	@Autowired
	private TicketClientService ticketClient;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserClientService userClient;
	
	public List<TicketUpdate> getAllTicketsUpdates(){
		List<TicketUpdate> list=ticketUpdateRepository.findAll();
		ArrayList message=new ArrayList();
		message.add("Empty List");
		if(list.isEmpty()) {
			list.addAll(message);
		}
		return list;
	}

	public Optional<TicketUpdate> getTicketUpdateById(Long updateId) {
		return ticketUpdateRepository.findById(updateId);
	}

	
	public ResponseEntity<Response> createTicketUpdate(TicketUpdate ticketUpdate) {
		List<String> message=new ArrayList<String>();
		Optional<TicketUpdate> getInfo=ticketUpdateRepository.findById(ticketUpdate.getUpdateId());
		
		Ticket tikcetDetails=ticketClient.getTicketById(ticketUpdate.getTicket().getTicketId()).orElseThrow(() -> new RuntimeException("Ticket not found"));
		User userDetails=userClient.getUser(ticketUpdate.getUpdatedBy().getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
		ticketUpdate.setTicket(tikcetDetails);
		ticketUpdate.setUpdatedBy(userDetails);
		ticketUpdate.setUpdateDate(LocalDateTime.now());
		TicketUpdate update= ticketUpdateRepository.save(ticketUpdate);
		if(update!=null) {
			emailService.sendEmail("${spring.mail.username}", userDetails.getEmail(), "New Ticket update Created", "Your Ticket updated has been Created. The Ticket Title is: "+tikcetDetails.getTitle()+" and Updated By: "+ticketUpdate.getUpdatedBy());
			message.add("Ticket Update Created Successfull");
		}else {
			message.add("Ticket Not Updated");
		}
		return ResponseEntity.ok(new Response(message));
	}

	public ResponseEntity<Response> updateTicketUpdate(Long updateId, TicketUpdate updateDetails) {
		List<String> message=new ArrayList<String>();
		Optional<TicketUpdate> ticketUpdate=ticketUpdateRepository.findById(updateId);
		User userDetails=userClient.getUser(updateDetails.getUpdatedBy().getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
	 
		try {
			if(updateDetails.getUpdatedBy()==null) {
				throw new IllegalArgumentException("Ticket Id Not Found");
			}
			if(ticketUpdate.isEmpty()) {
				throw new Exception("Ticket Details Not Found");
			}else {	
				if(ticketUpdate.isPresent()) {
					TicketUpdate update=ticketUpdate.get();
					update.setTicket(updateDetails.getTicket());
					update.setUpdateDescription(updateDetails.getUpdateDescription());
					update.setUpdateDate(LocalDateTime.now());
					update.setUpdatedBy(updateDetails.getUpdatedBy());
					ticketUpdateRepository.save(update);
					message.add("Ticket Updates Successfull");
					emailService.sendEmail("${spring.mail.username}", userDetails.getEmail(), "Ticket Updated", "Successs to Ticket send to IT Support team");
				}
			}
		}catch(IllegalArgumentException e) {
			message.add(e.getMessage());
		}catch(Exception e) {
			message.add(e.getMessage());
		}
		return ResponseEntity.ok(new Response(message));
	}

	public List<String> deleteTicketUpdate(Long updateId) {
		List<String> message=new ArrayList<String>();
    	Optional<TicketUpdate> getInfo=ticketUpdateRepository.findById(updateId);
    	TicketUpdate ticketDetails=ticketUpdateRepository.findById(updateId).get();
    	User userDetails=userClient.getUser(ticketDetails.getUpdatedBy().getUserId()).get();
    	try {
    		if(updateId==0) {
    			throw new IllegalArgumentException("Ticket Id Not Present");
    		}
    		if(getInfo.isEmpty()) {
    			throw new Exception("Ticket Details Not Found");
    		}
    		else {
    			
    			ticketUpdateRepository.deleteById(updateId);
    			emailService.sendEmail("${spring.mail.username}", userDetails.getEmail(), "Ticket Update.", "IT Supprot team Delete the Ticket Id is :"+ticketDetails.getTicket().getTitle());
    			message.add("Ticket Deleted Succssfull");
    		}
    	}catch(IllegalArgumentException e) {
    		message.add(e.getMessage());
    	}catch(Exception e) {
    		message.add(e.getMessage());
    	}
    	return message;
		
		
	}

}
