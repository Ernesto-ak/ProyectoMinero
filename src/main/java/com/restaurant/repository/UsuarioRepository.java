package com.restaurant.repository;

<<<<<<< HEAD
import java.util.Optional;
=======
import java.util.List;
>>>>>>> b9b7f56ff8941a7108d796c444224cb8e219f05c

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.model.Usuario;

<<<<<<< HEAD
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username); 
    Boolean existsByUsername(String username);
}


=======
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	List<Usuario> findByIdContainingIgnoreCase(Integer id);
}
>>>>>>> b9b7f56ff8941a7108d796c444224cb8e219f05c
