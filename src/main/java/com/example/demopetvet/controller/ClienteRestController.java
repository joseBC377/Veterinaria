package com.example.demopetvet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demopetvet.entity.Cliente;
import com.example.demopetvet.service.ClienteService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@RequestMapping(path = "api/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteRestController {

    @Autowired
    ClienteService service;

    @GetMapping()
    public List<Cliente> clienteSel() {
        return service.selectAll();
    }

    @GetMapping("/{id}")
    public Cliente clientesGet(@PathVariable Integer id) {
        return service.selectById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cliente clienteIns(@RequestBody Cliente cliente) {
        return service.insUpd(cliente);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cliente clienteUpd(@RequestBody Cliente cliente) {
        return service.insUpd(cliente);
    }

    @DeleteMapping(path = "/{id}")
    public String clienteDel(@PathVariable Integer id) {
        return service.delete(id);
    }

}
