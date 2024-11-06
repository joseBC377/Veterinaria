package com.example.demopetvet.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

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
import com.example.demopetvet.entity.Producto;
import com.example.demopetvet.service.CategoriaService;
import com.example.demopetvet.service.ProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("producto")
public class ProductoController {
    @Autowired
    ProductoService service;

    @Autowired
    CategoriaService categoriaService;

    @GetMapping()
    public String productoSel(Model model) {
        List<Producto> productos = service.selectAll();
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "PE"));

        productos.forEach(producto -> {
            String precioFormateado = formatoMoneda.format(producto.getPrecio());
            producto.setPrecioFormateado(precioFormateado);
        });
        model.addAttribute("productos", productos);
        return "intranet/producto";
    }

    // Ir al formulario
    @GetMapping("nuevo")
    public String productoInsertar(Model model) {
        List<Categoria> categorias = categoriaService.categoriaSel();
        model.addAttribute("categorias", categorias);

        if (categorias.isEmpty()) {
            model.addAttribute("mensajeError", "Por favor, agregue categorías antes de crear un producto.");
        }

        model.addAttribute("producto", new Producto());
        return "intranet/producto_form";
    }

    // Ir al formulario
    @GetMapping("editar/{id}")
    public String productoEditar(Model model, @PathVariable Integer id) {
        Producto producto = service.selectById(id);
        if (producto == null) {
            model.addAttribute("mensajeError", "Producto no encontrado");
            return "redirect:/producto";
        }
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.categoriaSel());
        return "intranet/producto_form";
    }

    // Procesar el formulario
    @PostMapping("guardar")
    public String productoGuardar(@Valid @ModelAttribute("producto") Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.categoriaSel());
            return "intranet/producto_form";
        }
        service.insUpd(producto);
        return "redirect:/producto";
    }

    // Eliminar
    @GetMapping("eliminar/{id}")
    public String productoEliminar(Model model, @PathVariable Integer id) {
        service.delete(id);
        return "redirect:/producto";
    }
}