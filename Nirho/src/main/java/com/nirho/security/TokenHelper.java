package com.nirho.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenHelper {

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String getUsernameFromToken(String token) {
        try {
            final Claims claims = this.getClaimsFromToken(token);
            return (String)claims.get("username");
        } catch (Exception e) {
            return null;
        }
    }
    
    public int getRoleFromToken(String token) {
        try {
            final Claims claims = this.getClaimsFromToken(token);
            return (int)claims.get("role");
        } catch (Exception e) {
            return -1;
        }
    }

    public String generateToken(String username, int role) {
    	String jws = Jwts.builder()
                .setSubject(username)
                .claim("username", username)
                .claim("role", role)
                .setIssuedAt(generateCurrentDate())
                .setExpiration(generateExpirationDate())
                .signWith( SIGNATURE_ALGORITHM, SECRET )
                .compact();
        return jws;
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private long getCurrentTimeMillis() {
        return new Date().getTime();
    }

    private Date generateCurrentDate() {
        return new Date(getCurrentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(getCurrentTimeMillis() + this.EXPIRES_IN * 1000);
    }
}
