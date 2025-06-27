package com.example.myapp.repository;

import java.util.List;
import java.util.Optional;

import com.example.myapp.controller.entity.Usuarios;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<Usuarios, String> {

    public Optional<Usuarios> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }
    
    public List<Usuarios> findActiveUsers(Page page) {
        return find("active", true)
            .page(page)
            .list();
    }
    
    public boolean existsByEmail(String email) {
        return count("email", email) > 0;
    }
    
    public List<Usuarios> findByNameContaining(String name, Page page) {
        return find("LOWER(name) LIKE LOWER(?1) AND active = true", "%" + name + "%")
            .page(page)
            .list();
    }
}
