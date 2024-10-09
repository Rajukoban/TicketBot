package com.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TutorialServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialServiceApplication.class, args);
	}

}
