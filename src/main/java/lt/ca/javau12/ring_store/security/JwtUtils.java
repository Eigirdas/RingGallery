package lt.ca.javau12.ring_store.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtils {
	
	private final SecretKey secretKey;
	

	private final long expirationMillis;
	
	public JwtUtils(
			@Value("${jwt.secret}") String secret,
			@Value("${jwt.expiration}") long expirationMilis) {
		
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		this.expirationMillis = expirationMilis;
	}
			
	
	
	
	public String generateToken(UserDetails userDetails) {
	    Instant now = Instant.now();
	    Collection<String> roles = userDetails.getAuthorities()
	            .stream()
	            .map(GrantedAuthority::getAuthority)
	            .collect(Collectors.toList());
	    return Jwts.builder()
	            .subject(userDetails.getUsername())
	            .claim("roles", roles)  
	            .issuedAt(Date.from(now))
	            .expiration(Date.from(now.plusMillis(expirationMillis)))
	            .signWith(secretKey)
	            .compact();
	}
	
	public String extractUsername(String token) {
	    if (token == null || !token.contains(".")) {
	        throw new IllegalArgumentException("Invalid JWT token");
	    }
		return Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	
	
    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        return getClaims(token).get("roles", List.class);
    }
	
	
	
	
	public boolean validateToken(String token, String username) {
		try {
			String extractedUsername = extractUsername(token);
			return extractedUsername.equals(username) && !isTokenExpired(token);
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean isTokenExpired(String token) {
	    if (token == null || !token.contains(".")) {
	        throw new IllegalArgumentException("Invalid JWT token");
	    }
		Date expiration = Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getExpiration();
		return expiration.before(new Date());
	}
	
    private Claims getClaims(String token) {
        if (token == null || !token.contains(".")) {
            throw new IllegalArgumentException("Invalid JWT token");
        }
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
	
	

}
