package com.restaurant.utilitarios;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtGenerator {

    // En producción, esto va en application.properties
    public static final String SECRET_KEY = "mi_clave_secreta_super_segura_y_larga_para_spring_security"; 

    // Generar Token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + 70000); // 70 segundos o lo que quieras

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    // Obtener Username del Token
    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Validar Token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
            .build()
            .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false; // Token expirado o inválido
        }
    }
}
