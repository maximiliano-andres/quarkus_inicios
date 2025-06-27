package com.example.myapp.service.interfaces;

public interface PasswordService {

    String hashPassword(String password);
    boolean verifyPassword(String password, String hashedPassword);

}
