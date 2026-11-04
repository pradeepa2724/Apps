package io.weatherfetcher.weatherapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRETKEY = "mysecretkeymysecretkeymysecretkeymysecretkey";
    public Key generateSignKey(){
        return Keys.hmacShaKeyFor(SECRETKEY.getBytes(StandardCharsets.UTF_8));
    }
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +1000*60*60))
                .signWith(generateSignKey())
                .compact();
    }
    public String extractUserName(String token){
        Claims claims= Jwts.parser()
                .setSigningKey(generateSignKey())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean isTokenValid(String token, String username){
        String extractedUsername= extractUserName(token);
        return extractedUsername.equals(username)&& !isTokenExpired(token);
    }
    public Boolean isTokenExpired(String token){
        Date expiration= Jwts.parser()
                .setSigningKey(generateSignKey())
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
