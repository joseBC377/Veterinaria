package com.example.demopetvet.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demopetvet.entity.Cliente;
import com.example.demopetvet.service.ClienteService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public String mostrarPerfil(Model model, Principal principal) {

        String email = principal.getName();
        Cliente cliente = clienteService.buscarCorreo(email);

        if (cliente == null) {
            return "redirect:/logout";
        }

        model.addAttribute("cliente", cliente);
        return "perfil";
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/guardar")
    public String guardarCambiosPerfil(@ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model,
            Principal principal) {
        if (result.hasErrors()) {
            return "perfil";
        }

        String email = principal.getName();
        Cliente clienteActual = clienteService.buscarCorreo(email);

        if (clienteActual == null) {
            return "redirect:/logout";
        }

        clienteActual.setNombre(cliente.getNombre());
        clienteActual.setApellido(cliente.getApellido());
        clienteActual.setTelefono(cliente.getTelefono());
        clienteActual.setDireccion(cliente.getDireccion());

        // Codifica la contraseña si se actualiza
        if (cliente.getContrasena() != null && !cliente.getContrasena().isEmpty()) {
            clienteActual.setContrasena(passwordEncoder.encode(cliente.getContrasena()));
        }

        clienteService.insUpd(clienteActual);

        model.addAttribute("cliente", clienteService.buscarCorreo(email));
        model.addAttribute("mensajeExito", "Campos actualizados correctamente.");
        return "perfil";
    }
}
