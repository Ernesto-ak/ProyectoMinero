package com.restaurant.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User; // Clase User de Spring Security
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restaurant.model.Rol;
import com.restaurant.model.Usuario;
import com.restaurant.repository.UsuarioRepository;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 1. Buscamos el usuario en la BD
		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el username: " + username));

		// 2. Retornamos un objeto User de Spring Security
		// Mapeamos: Nombre, Password, y convertimos los Roles a "Authorities"
		return new User(usuario.getUsername(), usuario.getPassword(), mapRolesToAuthorities(usuario.getRoles()));
	}

	// Método auxiliar para convertir tu lista de Roles (Entidad) a GrantedAuthority
	// (Spring Security)
	private Collection<GrantedAuthority> mapRolesToAuthorities(Set<Rol> roles) {
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}
}