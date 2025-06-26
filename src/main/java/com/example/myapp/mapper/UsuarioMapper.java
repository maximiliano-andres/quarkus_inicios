package com.example.myapp.mapper;

import com.example.myapp.entity.Usuarios;
import com.example.myapp.entity.dto.UsuarioDTO;

public interface UsuarioMapper {

    Usuarios toEntity(UsuarioDTO usuarioDTO);
    UsuarioDTO toDto(Usuarios usuario);
}
