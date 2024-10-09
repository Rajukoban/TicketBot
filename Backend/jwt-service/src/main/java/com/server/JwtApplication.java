package com.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//import io.jsonwebtoken.SignatureAlgorithm;

//import io.jsonwebtoken.security.Keys;
//import java.security.Key;
//import java.util.Base64;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
//		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // You can use HS512 or others
//        String base64UrlKey = Base64.getUrlEncoder().withoutPadding().encodeToString(key.getEncoded());
//        System.out.println("Base64URL Encoded Key: " + base64UrlKey);
	}

}
