package com.example.myapp.entity.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class LoginRequest {

    @Email(message = "Email debe ser válido")
    @NotBlank(message = "Email no puede estar vacío")
    private String email;

    @NotBlank(message = "Contraseña no puede estar vacía")
    private String password;

}
