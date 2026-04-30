package com.intoThe.utils;

import com.intoThe.exceptions.SuppliersOprException.JwtTokenGenerationException;
import com.intoThe.exceptions.SuppliersOprException.JwtTokenValidationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtils {
    private final SecretKey secreteKey = Keys.hmacShaKeyFor("my-secret-key".getBytes());

    public String generateJwtToken(String userName){
        String token = "";
        try{
            token = Jwts.builder()
                    .setSubject(userName)
                    .setIssuedAt(new Date())
                    .signWith(secreteKey)
                    .compact();
        }catch (Exception exception){
            throw new JwtTokenGenerationException("Error while token generation[" + exception.getMessage()+ "]");
        }
        return token;
    }

    public String getUserNameFromToken(String token){
        return getClaims(token).getSubject();
    }

    public Claims getClaims(String token){
        Claims claims;
        try {
            claims = Jwts.parser()//parser is deprecated change in the coming future.
                    .setSigningKey(secreteKey)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception exception){
            throw new JwtTokenValidationException("Error while extracting the username[" +exception.getMessage()+ "]");
        }
        return claims;
    }

    public Boolean validateToken(String token, String userName){
        try{
            String tokenUsername = getUserNameFromToken(token);
            if(tokenUsername.equalsIgnoreCase(userName)){
                return true;
            }
        }catch (Exception exception){
            throw new JwtTokenValidationException("Invalid token!.");
        }
        return false;
    }
}
