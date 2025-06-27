package com.example.myapp.entity.dto;

import java.time.LocalDateTime;

import com.example.myapp.entity.enumUser.UserRole;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String id;
    private String name;
    private String email;
    private UserRole role;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
