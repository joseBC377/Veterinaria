package com.example.demopetvet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
// SI SE TRABAJARA EN MODAL MANEJARLO POR UN Controller 
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("titulo", "Iniciar sesión");
        return "login";  // Devuelve la vista que contiene el modal
    }
}
