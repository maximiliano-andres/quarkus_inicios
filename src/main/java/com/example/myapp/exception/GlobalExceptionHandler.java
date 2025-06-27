package com.example.myapp.exception;

import java.time.LocalDateTime;

import com.example.myapp.exception.*;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Provider
@Slf4j
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        log.error("Exception caught: ", exception);

        if (exception instanceof UserNotFoundException) {
            return createErrorResponse(Response.Status.NOT_FOUND, exception.getMessage());
        }

        if (exception instanceof UserAlreadyExistsException) {
            return createErrorResponse(Response.Status.CONFLICT, exception.getMessage());
        }

        if (exception instanceof InvalidCredentialsException) {
            return createErrorResponse(Response.Status.UNAUTHORIZED, exception.getMessage());
        }

        // Error genérico
        return createErrorResponse(
                Response.Status.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred");
    }

    private Response createErrorResponse(Response.Status status, String message) {
        Map<String, Object> errorBody = Map.of(
                "error", true,
                "message", message,
                "timestamp", LocalDateTime.now(),
                "status", status.getStatusCode());

        return Response.status(status).entity(errorBody).build();
    }

}
