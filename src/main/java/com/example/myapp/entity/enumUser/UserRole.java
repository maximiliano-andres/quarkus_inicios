package com.example.myapp.entity.enumUser;

public enum UserRole {

    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getValue() {
        return role;
    }
}
