package com.server.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(nullable = false)
	private String recipient;
	
	@Column(nullable = false)
	private String subject;
	
	@Column(nullable = false)
	private String body;
	
	@Column(nullable = false)
	private LocalDateTime sentDate;

	public EmailNotification(String recipient, String subject, String body, LocalDateTime sentDate) {
		super();	
		this.recipient = recipient;
		this.subject = subject;
		this.body = body;
		this.sentDate = sentDate;
	}

}
