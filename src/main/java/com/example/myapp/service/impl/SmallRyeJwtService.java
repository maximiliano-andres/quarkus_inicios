package com.example.myapp.service.impl;

import java.security.PrivateKey;
import java.time.Duration;
import java.util.Set;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.example.myapp.service.interfaces.JwtService;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.util.KeyUtils;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class SmallRyeJwtService implements JwtService{

    @ConfigProperty(name = "app.security.jwt.issuer") // El emisor del JWT, por ejemplo, el nombre de tu aplicación
    String issuer;
    
    @ConfigProperty(name = "app.security.jwt.expiration") // La duración del token en milisegundos
    Long expiration;

    @Override
    public String generateToken(String userId, String email, String role){
        try {
            PrivateKey privateKey = KeyUtils.readPrivateKey("/META-INF/resources/privateKey.pem"); // Ruta al archivo de clave privada PEM
            
            return Jwt.issuer(issuer) // Establece el emisor del token
                .upn(email) // Establece el email del usuario como el nombre de usuario principal (UPN)
                .subject(userId) // Establece el ID de usuario como sujeto del token
                .groups(Set.of(role)) // Añade el rol del usuario como un grupo
                .claim("email", email) // Añade el email como un reclamo personalizado
                .expiresIn(Duration.ofSeconds(expiration)) // Establece la duración del token
                .sign(privateKey);  // Genera el token JWT firmado con la clave privada
                
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }
    
    @Override
    public boolean validateToken(String token){
        return true; // Implementa la lógica para validar el token
    }

    @Override
    public String extractUserIdFromToken(String token){
        return null; // Implementa la lógica para extraer el ID de usuario del token
    }
}
