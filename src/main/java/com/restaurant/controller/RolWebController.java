package com.restaurant.controller; // Ajusta a tu paquete

import com.restaurant.model.Rol;
import com.restaurant.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/roles")
public class RolWebController {

    @Autowired
    private RolRepository rolRepository;

    // LISTAR
    @GetMapping
    public String listarRoles(Model model) {
        model.addAttribute("roles", rolRepository.findAll());
        return "roles/lista";
    }

    // FORMULARIO NUEVO
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("rol", new Rol());
        return "roles/formulario";
    }

    // FORMULARIO EDITAR
    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de rol inválido"));
        model.addAttribute("rol", rol);
        return "roles/formulario";
    }

    // GUARDAR
    @PostMapping
    public String guardarRol(@ModelAttribute Rol rol) {
        rolRepository.save(rol);
        return "redirect:/web/roles";
    }

    // ELIMINAR
    @GetMapping("/{id}/eliminar")
    public String eliminarRol(@PathVariable Long id) {
        try {
            rolRepository.deleteById(id);
        } catch (Exception e) {
            // Si el rol está asignado a un usuario, dará error por llave foránea.
            // Para un sistema básico, simplemente redirigimos con un error en URL (opcional)
            return "redirect:/web/roles?error=No se puede eliminar un rol en uso";
        }
        return "redirect:/web/roles";
    }
}