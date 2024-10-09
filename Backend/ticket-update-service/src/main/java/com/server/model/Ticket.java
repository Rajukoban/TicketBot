package com.server.model;

import java.time.LocalDateTime;

import com.server.enums.TicketPriority;
import com.server.enums.TicketStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String description;
	
	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TicketStatus status;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TicketPriority priority;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;


}
