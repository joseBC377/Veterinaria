package com.example.demopetvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demopetvet.entity.Cliente;
import com.example.demopetvet.service.ClienteService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    ClienteService service;

    @GetMapping()
    public String clientesSel(Model model) {
        model.addAttribute("clientes", service.selectAll());
        return "intranet/clientes";
    }

    @GetMapping("nuevo")
    public String clienteIns(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "intranet/cliente_form";
    }

    @GetMapping("editar/{id}")
    public String clienteEditar(Model model, @PathVariable Integer id) {
        model.addAttribute("cliente", service.selectById(id));
        return "intranet/cliente_form";
    }
    @PostMapping("guardar")
    public String clienteGuardar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "intranet/cliente_form";
        }
    
        if (cliente.getId() != null) { // Edición
            Cliente clienteExistente = service.selectById(cliente.getId());
            
            // Mantener contraseña anterior si no se proporcionó una nueva
            if (cliente.getContrasena() == null || cliente.getContrasena().isEmpty()) {
                cliente.setContrasena(clienteExistente.getContrasena());
            }
    
            // Mantener el rol existente
            cliente.setRol(clienteExistente.getRol());
        } else { 
            // Asignar rol predeterminado si es un nuevo cliente
            cliente.setRol("ADMIN");
        }
        try {
            service.insUpd(cliente);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("mensajeError", "El correo ingresado ya está registrado. Por favor, use otro correo.");
            return "intranet/cliente_form";
        }
        return "redirect:/clientes";
    }

    // Eliminar
    @GetMapping("eliminar/{id}")
    public String clienteEliminar(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/clientes";
    }

}
