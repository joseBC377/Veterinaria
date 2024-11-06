package com.example.demopetvet.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demopetvet.entity.Servicio;
import com.example.demopetvet.service.ServicioService;

@Controller
@RequestMapping("servicio")
public class ServicioController {

    @Autowired
    private ServicioService service;

    @GetMapping
    public String selectAll(Model model) {
        List<Servicio> servicios = service.selectAll(); 
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "PE"));
        servicios.forEach(servicio -> {
            String precioOriginal = servicio.getPrecio(); 
            String precioFormateado = formatoMoneda.format(Double.parseDouble(precioOriginal)); 
            servicio.setPrecio(precioFormateado);
        });
        model.addAttribute("servicios", servicios);
        return "intranet/servicio"; 
    }
    
    // Ir al formulario de nuevo servicio
    @GetMapping("nuevo")
    public String servicioInsertar(Model model) {
        model.addAttribute("servicio", new Servicio()); 
        return "intranet/servicio_form"; 
    }

    // Guardar Servicio 
    @PostMapping("/guardar")
    public String guardarServicio(Servicio servicio, BindingResult result) {
        if (result.hasErrors()) {
            return "intranet/servicio_form"; 
        }
        service.insUpd(servicio); 
        return "redirect:/servicio"; 
    }

    // Ir al formulario de edición
    @GetMapping("editar/{id}")
    public String servicioEditar(Model model, @PathVariable Integer id) {
        Servicio servicio = service.selectById(id); 
        model.addAttribute("servicio", servicio); 
        return "intranet/servicio_form";
    }

    // Eliminar un servicio
    @GetMapping("eliminar/{id}")
    public String servicioEliminar(@PathVariable Integer id) {
        service.delete(id); 
        return "redirect:/servicio"; 
    }
}