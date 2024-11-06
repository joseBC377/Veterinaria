package com.example.demopetvet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demopetvet.entity.Contactanos;
// import com.example.demopetvet.service.CategoriaService;
import com.example.demopetvet.service.ContactanosService;
import com.example.demopetvet.service.ServicioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("contactanos")
public class ContactanosController {

    @Autowired
    ContactanosService service;

    @Autowired
    ServicioService servicioService;
    
    @GetMapping("edicionContactanos")
    public String contactanosSel(Model model) {
        List<Contactanos> contactanos = service.selectAll();
        model.addAttribute("edicionContactanos",contactanos);
        return "edicionContactanos";
    }
    //Ir al formulario
    @GetMapping()
    public String contactanosIns(Model model) {
        model.addAttribute("contacto", new Contactanos());
        model.addAttribute("servicios", servicioService.selectAllWithSizeLog());
        return "contactanos";
    }
    //Ir al formulario
    @GetMapping("editar/{id}")
    public String contactanosEditar(Model model, @PathVariable Integer id  ) {
        model.addAttribute("contacto", service.selectById(id) );
        model.addAttribute("servicios", servicioService.selectAllWithSizeLog());
        return "contactanos";
    }
    //Procesar el formulario
    @PostMapping("guardar")
    public String contactoGuardar(@ModelAttribute Contactanos contacto, BindingResult result) {
        if (result.hasErrors()) {
            return "contactanos";
        }
        service.insUpd(contacto);
        return "redirect:/contactanos";
    }
    //Eliminar
    @GetMapping("eliminar/{id}")
    public String contactoEliminar(Model model, @PathVariable Integer id ) {
        service.delete(id);
        return "redirect:/contactanos/edicionContactanos";
    }

}
