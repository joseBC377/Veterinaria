package com.example.demopetvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    ClienteService service;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registro";
    }

    @PostMapping("/registro/guardar")
    public String clienteGuardar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Retornar la vista de registro con los errores de validación
            return "registro";
        }

        // Asignar el rol por defecto
        cliente.setRol("CLIENT");

        try {
            service.insUpd(cliente);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("mensajeError", "El correo ingresado ya está registrado. Por favor, use otro correo.");
            return "registro";
        }
        return "redirect:/intranet_usuario";
    }
}