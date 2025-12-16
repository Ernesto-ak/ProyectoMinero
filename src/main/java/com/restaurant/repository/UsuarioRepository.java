package com.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username); 
    Boolean existsByUsername(String username);
}


