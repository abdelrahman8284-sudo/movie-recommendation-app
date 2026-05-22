package com.movie.backend.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.movie.backend.security.dto.MyUserPrinciple;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.token.key}")
	private String secretKey;
	
	public String generateToken(MyUserPrinciple user) {
		Map<String,Object> claims = new HashMap<>();
		claims.put("userId", user.getId());
		claims.put("username", user.getUsernameValue());
		claims.put("email", user.getUsername());
		claims.put("role", user.getRole());
		return Jwts.builder()
				.claims()
				.add(claims)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15))
				.subject(user.getUsername())
				.and()
				.signWith(getKey())
				.compact();
	}

	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}
	// validate token --> by email , expiration of token
	public boolean isTokenValid(String token,UserDetails user) {
		// اولا استخراج ال email من ال token 
		String email = extractEmail(token);
		return (email.equals(user.getUsername()) && !isTokenExpired(token));
	}
	
	public <T> T extractClaim(String token , Function<Claims,T > resolver) {
		Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
		
	}

	private Claims extractAllClaims(String token) {
		// من ال token مع التأكد من صحة ال key اللي عملته 
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public String extractEmail(String token) {
		
		return extractClaim(token,Claims::getSubject);
	}
	
	private boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		
		return extractClaim(token,Claims::getExpiration);
	}
}
