package com.example.demopetvet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demopetvet.entity.Categoria;
import com.example.demopetvet.service.CategoriaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @GetMapping()
    public String categoriaSel(Model model) {
        List<Categoria> categorias = service.categoriaSel();
        model.addAttribute("categorias", categorias);
        return "intranet/categoria";
    }

    // Ir al formulario
    @GetMapping("nuevo")
    public String categoriaInsertar(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "intranet/categoria_form";
    }

    // Ir al formulario
    @GetMapping("editar/{id}")
    public String categoriaEditar(Model model, @PathVariable Integer id) {
        model.addAttribute("categoria", service.categoriaGet(id));
        return "intranet/categoria_form";
    }

    // Procesar el formulario
    @PostMapping("guardar")
    public String categoriaGuardar(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result) {
        if (result.hasErrors()) {
            return "intranet/categoria_form";
        }
        service.categoriaInsUpd(categoria);
        return "redirect:/categoria";
    }

    // Eliminar
    @GetMapping("eliminar/{id}")
    public String categoriaEliminar(@PathVariable Integer id) {
        service.categoriaDel(id); 
        return "redirect:/categoria";
    }
}
