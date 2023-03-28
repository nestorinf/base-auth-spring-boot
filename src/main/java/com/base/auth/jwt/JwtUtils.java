package com.base.auth.jwt;

import com.base.auth.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtils {
    private final static String SECRET_KEY = "secret12";

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean validateJwtToken(String token) {

        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (SignatureException e) {
            System.out.println("Error Signature: " + e.getMessage());
        }catch (MalformedJwtException e) {
            System.out.println("Error invalid Token: " + e.getMessage());
        }catch (ExpiredJwtException e ) {
            System.out.println("Error invalid Token Expired: " + e.getMessage());
        }catch (IllegalArgumentException e ) {
            System.out.println("Error invalid Token claims: " + e.getMessage());
        }
        return false;
    }

    public boolean revokeToken(String token) {
//        Jwts.builder().r
        return false;
    }

}
