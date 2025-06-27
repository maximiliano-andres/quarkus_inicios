package com.example.myapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.example.myapp.controller.entity.Usuarios;
import com.example.myapp.controller.entity.dto.AuthResponse;
import com.example.myapp.controller.entity.dto.CreateUserRequest;
import com.example.myapp.controller.entity.dto.LoginRequest;
import com.example.myapp.controller.entity.dto.UpdateUserRequest;
import com.example.myapp.controller.entity.dto.UserResponse;
import com.example.myapp.exception.InvalidCredentialsException;
import com.example.myapp.exception.UserAlreadyExistsException;
import com.example.myapp.exception.UserNotFoundException;
import com.example.myapp.mapper.UserMapper;
import com.example.myapp.repository.UserRepository;
import com.example.myapp.service.interfaces.JwtService;
import com.example.myapp.service.interfaces.PasswordService;
import com.example.myapp.service.interfaces.UserService;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;
    
    @Inject
    PasswordService passwordService;
    
    @Inject
    JwtService jwtService;
    
    @Inject
    UserMapper userMapper;
    
    @ConfigProperty(name = "app.security.jwt.expiration")
    Long jwtExpiration;
    
    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        log.debug("Creating user with email: {}", request.getEmail());
        
        // Verificar si el usuario ya existe
        if (userRepository.existsByEmail(request.getEmail().toLowerCase())) {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }
        
        // Crear usuario
        Usuarios user = userMapper.toEntity(request);
        user.setPassword(passwordService.hashPassword(request.getPassword()));
        
        userRepository.persist(user);
        
        log.info("User created successfully with ID: {}", user.getId());
        return userMapper.toResponse(user);
    }
    
    @Override
    public UserResponse getUserById(String id) {
        log.debug("Fetching user by ID: {}", id);
        
        Usuarios user = userRepository.findByIdOptional(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
            
        return userMapper.toResponse(user);
    }
    
    @Override
    @Transactional
    public UserResponse updateUser(String id, UpdateUserRequest request) {
        log.debug("Updating user with ID: {}", id);
        
        Usuarios user = userRepository.findByIdOptional(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        
        // Actualizar campos si están presentes
        if (request.getNombre() != null && !request.getNombre().trim().isEmpty()) {
            user.setName(request.getNombre());
        }
        
        if (request.getEmail() != null && !request.getEmail().trim().isEmpty()) {
            String newEmail = request.getEmail().toLowerCase();
            if (!user.getEmail().equals(newEmail) && userRepository.existsByEmail(newEmail)) {
                throw new UserAlreadyExistsException("Email " + newEmail + " is already in use");
            }
            user.setEmail(newEmail);
        }
        
        userRepository.persist(user);
        
        log.info("User updated successfully with ID: {}", id);
        return userMapper.toResponse(user);
    }
    
    @Override
    @Transactional
    public void deleteUser(String id) {
        log.debug("Deleting user with ID: {}", id);
        
        Usuarios user = userRepository.findByIdOptional(id) // Buscar usuario por ID
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        
        userRepository.delete(user); // eliminar el usuario
        
        log.info("User soft deleted with ID: {}", id);
    }

    /*

    //activa o desactiva el campo Activate
    @Override
    @Transactional
    public void deleteUser(String id) {
        log.debug("Deleting user with ID: {}", id);
        
        Usuarios user = userRepository.findByIdOptional(id) // Buscar usuario por ID
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        
        // Soft delete
        user.setActive(false); // activar campo de usuario
        
        userRepository.persist(user); // Persistir el cambio
        
        log.info("User soft deleted with ID: {}", id);
    }
    
     */
    
    @Override
    public List<UserResponse> getAllUsers(int page, int size) {
        log.debug("Fetching users - page: {}, size: {}", page, size);
        
        Page pageRequest = Page.of(page, size);
        List<Usuarios> users = userRepository.findActiveUsers(pageRequest);
        
        return users.stream()
            .map(userMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    public AuthResponse authenticate(LoginRequest request) {
        log.debug("Authenticating user with email: {}", request.getEmail());
        
        Usuarios user = userRepository.findByEmail(request.getEmail().toLowerCase())
            .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));
        
        if (!user.getActive()) {
            throw new InvalidCredentialsException("Account is deactivated");
        }
        
        if (!passwordService.verifyPassword(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        
        String token = jwtService.generateToken(
            user.getId(), 
            user.getEmail(), 
            user.getRole().getValue()
        );
        
        log.info("Usuario Autentificado por email: {}", user.getEmail());
        
        AuthResponse authResponse = AuthResponse.builder()
            .token(token)
            .expiresIn(jwtExpiration)
            .user(userMapper.toResponse(user))
            .build();

        log.info(authResponse.toString());
        return authResponse ;
    }

}
