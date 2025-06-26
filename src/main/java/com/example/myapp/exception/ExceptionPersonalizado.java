package com.example.myapp.exception;

import java.util.NoSuchElementException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionPersonalizado implements ExceptionMapper<NoSuchElementException> {

    public static record NoSuchElementMessage(String message, String detail) {
    }

    @Override
    public Response toResponse(NoSuchElementException exception) {
        var error = new NoSuchElementMessage(exception.getMessage(), "error personalizado");

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
