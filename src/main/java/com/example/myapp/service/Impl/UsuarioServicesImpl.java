package com.example.myapp.service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.myapp.entity.dto.UsuarioDTO;
import com.example.myapp.service.UsuarioServices;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioServicesImpl implements UsuarioServices {

    List<UsuarioDTO> listaUsuariosDTO = new ArrayList<>();

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO){
        listaUsuariosDTO.add(usuarioDTO);
        return usuarioDTO;
    }

    @Override
    public Optional<UsuarioDTO> obtenerUsuarioPorId(Long id) {
        return listaUsuariosDTO.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findAny();
    }


    @Override
    public List<UsuarioDTO> listarUsuarios(){
        return Collections.unmodifiableList(listaUsuariosDTO);
    }

    public Boolean isEmpty(){
        return listaUsuariosDTO.isEmpty();
    }

}
