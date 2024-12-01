package com.example.demopetvet.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demopetvet.entity.Servicio;
import com.example.demopetvet.service.ServicioService;


@Controller
@RequestMapping("/servicios")
public class ServicioPublicController {

    @Autowired
    private ServicioService service;

    @GetMapping
    public String selectAll(Model model) {
        List<Servicio> servicios = service.selectAll();
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "PE"));

        servicios.forEach(servicio -> {
            String precioFormateado = formatoMoneda.format(servicio.getPrecio());
            servicio.setPrecioFormateado(precioFormateado);
        });

        model.addAttribute("servicios", servicios);
        return "servicios"; 
    }
}