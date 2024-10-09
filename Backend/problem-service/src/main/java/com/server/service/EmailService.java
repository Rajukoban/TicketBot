package com.server.service;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.server.model.EmailNotification;
import com.server.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	@Autowired
	private EmailRepository emailRepository;
	
	public void sendEmail(String from,String to,String subject,String text) {
		try {
		SimpleMailMessage message=new SimpleMailMessage();
	    message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);
		
		EmailNotification emailEntity=new EmailNotification(to,subject,text,LocalDateTime.now());
		emailRepository.save(emailEntity);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Faild to send email",e);
		}
	}

}
