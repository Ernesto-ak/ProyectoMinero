package com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restaurant.model.Usuario;
import com.restaurant.repository.RolRepository;
import com.restaurant.repository.UsuarioRepository;


@Controller
@RequestMapping("/web/usuarios")
public class UsuarioWebController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // LISTAR
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios/lista";
    }

    // FORMULARIO NUEVO
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("todosLosRoles", rolRepository.findAll());
        return "usuarios/formulario";
    }

    // FORMULARIO EDITAR
    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido"));
        model.addAttribute("usuario", usuario);
        model.addAttribute("todosLosRoles", rolRepository.findAll());
        return "usuarios/formulario";
    }

    // GUARDAR (CREATE / UPDATE)
    @PostMapping
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        // Si el usuario ya existe y no mandaron password nuevo, mantenemos el viejo
        if (usuario.getId() != null) {
        	Usuario existente = usuarioRepository.findById(usuario.getId()).orElse(null);
            if (existente != null && (usuario.getPassword() == null || usuario.getPassword().isEmpty())) {
                usuario.setPassword(existente.getPassword());
            } else {
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }
        } else {
            // Nuevo usuario
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        
        usuarioRepository.save(usuario);
        return "redirect:/web/usuarios";
    }

    // ELIMINAR
    @GetMapping("/{id}/eliminar")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/web/usuarios";
    }
}