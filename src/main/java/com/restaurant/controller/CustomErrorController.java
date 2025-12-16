package com.restaurant.controller;

import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // 1. Obtenemos la autenticación actual
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 2. Lógica de decisión
        if (auth != null && apiEsAuthenticated(auth)) {
            return "redirect:/web/restaurants";
        }
        
        // Caso: El usuario NO está logueado o es anónimo -> Al Login
        return "redirect:/login";
    }

    // Método auxiliar para saber si es un usuario real y no un "anonymousUser"
    private boolean apiEsAuthenticated(Authentication auth) {
        return auth.isAuthenticated() && 
               !(auth instanceof AnonymousAuthenticationToken);
    }
}