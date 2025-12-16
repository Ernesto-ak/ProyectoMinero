package com.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.security.crypto.password.PasswordEncoder;
=======
>>>>>>> b9b7f56ff8941a7108d796c444224cb8e219f05c
import org.springframework.stereotype.Service;

import com.restaurant.model.Usuario;
import com.restaurant.repository.UsuarioRepository;

@Service
public class UsuarioService {

<<<<<<< HEAD
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    
    
    
    
=======
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> obtenerTodos() {
		return usuarioRepository.findAll();
	}
>>>>>>> b9b7f56ff8941a7108d796c444224cb8e219f05c
}
