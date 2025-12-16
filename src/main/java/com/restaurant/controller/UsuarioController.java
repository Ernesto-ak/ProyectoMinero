package com.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.model.Usuario;
import com.restaurant.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint público para registrar usuarios
    @PostMapping("/auth/registro")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.guardarUsuario(usuario), HttpStatus.CREATED);
    }

    // Endpoint protegido (requiere estar logueado)
    @GetMapping("/users")
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }
    
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok("Usuario eliminado");
    }
    
}
