package com.example.myapp.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.myapp.entity.dto.UsuarioDTO;
import com.example.myapp.service.Impl.UsuarioServicesImpl;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosControllers {

    @Inject
    private UsuarioServicesImpl userIMPL;

    @POST
    @Path("/crear")
    public UsuarioDTO crearUsuario(UsuarioDTO usaurioDTO){
        return userIMPL.crearUsuario(usaurioDTO);
    }

    @GET
    @Path("/{id}")
    public UsuarioDTO obtenerPorId(@PathParam("id") Long id) {
        Optional<UsuarioDTO> usuario = userIMPL.obtenerUsuarioPorId(id);
        
        return usuario.orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con el ID: " + id));
    }

    @GET
    @Path("/listar")
    public List<UsuarioDTO> listarUsuarios(){
        if(userIMPL.isEmpty()) {
            return List.of(); // Retorna una lista vacía si no hay usuarios
        }
        return userIMPL.listarUsuarios();
    }
}
