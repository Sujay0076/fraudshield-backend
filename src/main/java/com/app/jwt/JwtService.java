package com.app.jwt;

import java.util.Date;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	@Value("${jwt.secret}")
	private String SECRET;
	
	public String generateToken(String email,String role) {
		Key key = new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS256.getJcaName());
		
		return Jwts.builder()
				.setSubject(email)
				.claim("role", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(key)
				.compact();
	}
	
	public boolean validateToken(String token) {

	    try {
	        extractClaims(token);
	        return true;
	    } catch(Exception e) {
	        return false;
	    }
	}
	
	public Claims extractClaims(String token) {
		Key key = new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS256.getJcaName());
		
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String extractEmail(String token) {
		
		return extractClaims(token).getSubject();
	}

	public String extractRole(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token).get("role",String.class);
	}
	

}
