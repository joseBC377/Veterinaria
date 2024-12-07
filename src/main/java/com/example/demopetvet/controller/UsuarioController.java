package com.example.demopetvet.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UsuarioController {

    // @SuppressWarnings("null")
    @GetMapping("/index")
    public String index(Model model, Authentication authentication) {
        // Verificar si el usuario está autenticado
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getPrincipal());

        // Añadir el estado de autenticación al modelo
        model.addAttribute("isAuthenticated", isAuthenticated);

        // Redirigir según el rol del usuario autenticado
        if (isAuthenticated) {
            if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/intranet"; // Página para administradores
            } else if (authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
                return "redirect:/perfil"; // Página para usuarios
            }
        }

        // Página de inicio para invitados
        return "index";
    }

    @GetMapping("/intranet")
    public String intranet(Model model, Authentication authentication) {
        // Determinar la URL del "home" según el rol
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("homeUrl", "/intranet");
        } else if (authentication != null
                && authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
            model.addAttribute("homeUrl", "/perfil");
        } else {
            model.addAttribute("homeUrl", "/index");
        }
        return "intranet";
    }
}
