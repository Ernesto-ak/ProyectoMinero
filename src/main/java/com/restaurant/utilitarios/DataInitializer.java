package com.restaurant.utilitarios; // O tu paquete

import com.restaurant.model.Rol;
import com.restaurant.model.Usuario;
import com.restaurant.repository.RolRepository;
import com.restaurant.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner initData(UsuarioRepository usuarioRepo, RolRepository rolRepo, PasswordEncoder passwordEncoder) {
		return args -> {
			Rol adminRole = rolRepo.findByNombre("Administrador").orElseGet(() -> {
				Rol nuevoRol = new Rol();
				nuevoRol.setNombre("ROLE_ADMIN");
				return rolRepo.save(nuevoRol);
			});

			if (usuarioRepo.findByUsername("admin").isEmpty()) {
				Usuario admin = new Usuario();
				admin.setUsername("admin");
				// AQUÍ defines la contraseña, el encriptador la hashea
				admin.setPassword(passwordEncoder.encode("12345"));
				admin.setRoles(Set.of(adminRole)); // Asigna el rol
				usuarioRepo.save(admin);
				System.out.println("--- USUARIO ADMIN CREADO (User: admin / Pass: 12345) ---");
			}
		};
	}
}