package com.example.myapp.service.impl;

import com.example.myapp.service.interfaces.PasswordService;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BCryptPasswordService implements PasswordService{

    private static final int STRENGTH = 12; // ajusta el valor de fuerza según tus necesidades

    @Override
    public String hashPassword(String password){
        return BCrypt.withDefaults().hashToString(STRENGTH, password.toCharArray()); // devuelve el hash de la contraseña
    }

    @Override
    public boolean verifyPassword(String password, String hashedPassword){
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified; // devuelve true si la contraseña coincide
    }

}
