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
import com.example.demopetvet.entity.Cliente;
import com.example.demopetvet.entity.Reserva;
import com.example.demopetvet.entity.Servicio;
import com.example.demopetvet.service.ClienteService;
import com.example.demopetvet.service.ReservaService;
import com.example.demopetvet.service.ServicioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("reserva")
public class ReservaController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ReservaService service;

    @GetMapping()
    public String selectAll(Model model) {
        List<Reserva> reservas = service.selectAll();
        List<Servicio> servicios = servicioService.selectAll();
        List<Cliente> clientes = clienteService.selectAll();
        model.addAttribute("reservas", reservas);
        model.addAttribute("servicios", servicios);
        model.addAttribute("clientes", clientes);
        return "intranet/reserva";
    }

    // Ir al formulario
    @GetMapping("editar/{id}")
    public String reservaEditar(Model model, @PathVariable Integer id) {
        Reserva reserva = service.selectById(id);
        model.addAttribute("reservas", reserva);
        model.addAttribute("clientes", clienteService.selectAll());
        model.addAttribute("servicios", servicioService.selectAll());
        return "intranet/reserva_form";
    }

    // Ir al formulario
    // Ir al formulario para crear nueva reserva
    @GetMapping("nuevo")
    public String reservaInsertar(Model model) {
        List<Servicio> servicios = servicioService.selectAll();
        List<Cliente> clientes = clienteService.selectAll();
        model.addAttribute("servicios", servicios);
        model.addAttribute("clientes", clientes);
        
        // Validación para servicios
        if (servicios.isEmpty()) {
            model.addAttribute("mensajeErrorServicio", "Por favor, agregue servicios antes de crear una reserva.");
        }

        // Validación para clientes
        if (clientes.isEmpty()) {
            model.addAttribute("mensajeErrorCliente", "Por favor, agregue clientes antes de crear una reserva.");
        }

        model.addAttribute("reservas", new Reserva());

        return "intranet/reserva_form";
    }

    // Actualizar Reserva
    @PostMapping("guardar")
    public String reservaGuardar(@Valid @ModelAttribute("reservas") Reserva reserva, BindingResult result,  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("servicios",servicioService.selectAll());
            model.addAttribute("clientes",clienteService.selectAll());
            return "intranet/reserva_form";
        }
        service.insUpd(reserva);
        return "redirect:/reserva";
    }

    // Eliminar una reserva
    @GetMapping("eliminar/{id}")
    public String reservaEliminar(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/reserva";
    }

}
