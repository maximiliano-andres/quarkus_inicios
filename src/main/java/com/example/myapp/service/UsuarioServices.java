package com.example.myapp.service;

import java.util.List;
import java.util.Optional;

import com.example.myapp.entity.dto.UsuarioDTO;

public interface UsuarioServices {

    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    Optional<UsuarioDTO> obtenerUsuarioPorId(Long id);
    List<UsuarioDTO> listarUsuarios();
    
}
