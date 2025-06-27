package com.example.myapp.controller.entity.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    private String token;
    private Long expiresIn;
    private UserResponse user;
    
    @Builder.Default
    private String tokenType = "Bearer";

}
