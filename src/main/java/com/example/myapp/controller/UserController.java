package com.example.myapp.controller;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.example.myapp.entity.dto.AuthResponse;
import com.example.myapp.entity.dto.CreateUserRequest;
import com.example.myapp.entity.dto.LoginRequest;
import com.example.myapp.entity.dto.UpdateUserRequest;
import com.example.myapp.entity.dto.UserResponse;
import com.example.myapp.service.interfaces.UserService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;


import java.util.List;


@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "User Management", description = "User CRUD operations")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Inject
    UserService userService;
    
    @POST
    @Path("/register")
    @Operation(summary = "Register a new user")
    public Response createUser(@Valid CreateUserRequest request) {
        UserResponse response = userService.createUser(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }
    
    @POST
    @Path("/login")
    @Operation(summary = "Authenticate user")
    public Response login(@Valid LoginRequest request) {
        AuthResponse response = userService.authenticate(request);
        return Response.ok(response).build();
    }
    
    @GET
    @Path("/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    @SecurityRequirement(name = "jwt")
    @Operation(summary = "Get user by ID")
    public Response getUserById(@PathParam("id") String id) {
        UserResponse response = userService.getUserById(id);
        return Response.ok(response).build();
    }
    
    @PUT
    @Path("/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    @SecurityRequirement(name = "jwt")
    @Operation(summary = "Update user")
    public Response updateUser(@PathParam("id") String id, @Valid UpdateUserRequest request) {
        UserResponse response = userService.updateUser(id, request);
        return Response.ok(response).build();
    }
    
    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    @SecurityRequirement(name = "jwt")
    @Operation(summary = "Delete user (Admin only)")
    public Response deleteUser(@PathParam("id") String id) {
        userService.deleteUser(id);
        return Response.noContent().build();
    }
    
    @GET
    @RolesAllowed({"ADMIN"})
    @SecurityRequirement(name = "jwt")
    @Operation(summary = "Get all users (Admin only)")
    public Response getAllUsers(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        List<UserResponse> response = userService.getAllUsers(page, size);
        return Response.ok(response).build();
    }
}
