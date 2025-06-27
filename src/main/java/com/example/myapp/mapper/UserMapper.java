package com.example.myapp.mapper;

import com.example.myapp.controller.entity.Usuarios;
import com.example.myapp.controller.entity.dto.CreateUserRequest;
import com.example.myapp.controller.entity.dto.UserResponse;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {

    public Usuarios toEntity(CreateUserRequest request) {
        return Usuarios.builder()
            .name(request.getNombre())
            .email(request.getEmail().toLowerCase())
            .build();
    }
    
    public UserResponse toResponse(Usuarios user) {
        return UserResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .role(user.getRole())
            .active(user.getActive())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }
}
 //REVISAR