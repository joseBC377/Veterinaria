package com.example.demopetvet.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demopetvet.entity.Producto;
import com.example.demopetvet.service.CategoriaService;
import com.example.demopetvet.service.ProductoService;

@Controller

public class productosController {
    @Autowired
    ProductoService service;
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/productos")
    public String verProductos(Model model) {
        List<Producto> productos = service.selectAll();
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "PE"));

        productos.forEach(producto -> {
            String precioFormateado = formatoMoneda.format(producto.getPrecio());
            producto.setPrecioFormateado(precioFormateado);
        });
        model.addAttribute("productos", productos);
        return "productos";
    }
}
