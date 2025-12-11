package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	List<Usuario> findByIdContainingIgnoreCase(Integer id);
}
