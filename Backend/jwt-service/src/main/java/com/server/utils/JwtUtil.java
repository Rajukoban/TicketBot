package com.server.utils;

import java.security.Key;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
	
private static String SECRET="eyJhbGciOiJIUzUxMiJ9eyJzdWIiOiJhZG1pbjEyMyIsImV4cCI6MTcwMTA5NTM1MiwiaWF0IjoxNzAxMDc3MzUyfQYBd9PrqOV0q6hF9eW9sJiduTFusRuDErvXCnvIrGdOdKRmUaCbVM_Jo85Jep77VzfOUuXfZkhoYgokxRG1U0hg";
 
	
	public String generateToken(String userName) {
		Map<String,Object> claims=new HashMap<>();
		return createToken(claims,userName);
	}

	private String createToken(Map<String, Object> claims, String userName) {
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+10000*60*30))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		SECRET=SECRET.replace('-','+').replace('_', '/');
		byte[] keybytes=Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keybytes);
	}
	
	public String exactUsername(String token) {
		return exactClaim(token,Claims::getSubject);
	}

	public <T> T exactClaim(String token,Function<Claims,T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date(System.currentTimeMillis()));
	}

	private java.util.Date extractExpiration(String token) {
		return exactClaim(token, Claims::getExpiration);
	}

	public Boolean validateToken(String token,UserDetails userDetails) {
		final String username=exactUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public String extractUsername(String token) {
		return Jwts.parser().setSigningKey(SECRET)
	.parseClaimsJws(token)
	.getBody().getSubject();
	}
}
