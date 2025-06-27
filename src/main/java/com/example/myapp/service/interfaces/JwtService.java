package com.example.myapp.service.interfaces;

public interface JwtService {

    String generateToken(String userId, String email, String role);
    boolean validateToken(String token);
    String extractUserIdFromToken(String token);
}
