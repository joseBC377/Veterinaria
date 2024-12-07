package com.example.demopetvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demopetvet.entity.Cliente;
import com.example.demopetvet.service.ClienteService;

import jakarta.validation.Valid;

@Controller
public class RegistroController {

    @Autowired
    private ClienteService clienteService;
    @GetMapping("/registrarse")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registro"; 
    }

    // Procesar el formulario de registro
    @PostMapping("/registrarse")
    public String procesarFormularioRegistro(@Valid @ModelAttribute("cliente") Cliente cliente,
                                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registro";
        }

        try {
            clienteService.registrarCliente(cliente);
            return "redirect:/intranet_usuario";  
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error al registrar el cliente: " + e.getMessage());
            return "registro";
        }
    }
}
