package com.plazit.market.web.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String Key= "llave";

    public String generarToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date( System.currentTimeMillis() +100 * 60 * 60 *10))
                .signWith(SignatureAlgorithm.HS256,Key).compact();
    }
}
