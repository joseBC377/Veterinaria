package com.example.demopetvet.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demopetvet.entity.Servicio;
import com.example.demopetvet.service.ServicioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("servicio")
public class ServicioPrivateController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\guardadoServicio";

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
    public String guardarServicio(
        @Valid @ModelAttribute("servicio") Servicio servicio, @RequestParam("img") 
        MultipartFile file, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            return "intranet/servicio_form"; 
        }

        try {
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());

            servicio.setImagen(file.getOriginalFilename());
        } catch (Exception e) {
            model.addAttribute("msg", "Error al subir la imagen: " + e.getMessage());
            model.addAttribute("categorias", service.selectAll());
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