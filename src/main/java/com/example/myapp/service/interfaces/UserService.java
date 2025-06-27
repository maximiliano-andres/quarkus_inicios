package com.example.myapp.service.interfaces;

import java.util.List;

import com.example.myapp.entity.dto.AuthResponse;
import com.example.myapp.entity.dto.CreateUserRequest;
import com.example.myapp.entity.dto.LoginRequest;
import com.example.myapp.entity.dto.UpdateUserRequest;
import com.example.myapp.entity.dto.UserResponse;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);
    UserResponse getUserById(String id);
    UserResponse updateUser(String id, UpdateUserRequest request);
    void deleteUser(String id);
    List<UserResponse> getAllUsers(int page, int size);
    AuthResponse authenticate(LoginRequest request);

}
