package com.example.demopetvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demopetvet.entity.Cliente;
import com.example.demopetvet.service.ClienteService;
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
    //Procesar el formulario
    @PostMapping("guardar")
    public String inquilinoGuardar(@Validated @ModelAttribute Cliente cliente, BindingResult result) {        
        if(result.hasErrors()){
            return "intranet/inquilino_form";
        }
        service.insUpd(cliente);
        return "redirect:/clientes";
    }
    // Eliminar
    @GetMapping("eliminar/{id}")
    public String inquilinoEliminar(@PathVariable Integer id) {
    service.delete(id); 
    return "redirect:/clientes"; 
    }

}
