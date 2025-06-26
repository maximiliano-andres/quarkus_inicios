package com.example.myapp.entity;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Usuarios {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;

}
