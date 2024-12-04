package com.example.demopetvet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demopetvet.entity.Cliente;
import com.example.demopetvet.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    // select* from
    public List<Cliente> selectAll() {
        return repository.findAll();
    }

    // Select *from where
    public Cliente selectById(Integer id) {
        return repository.findById(id).orElse(new Cliente());
    }

    // Insert -Update
    public Cliente insUpd(Cliente cliente) {
        return repository.save(cliente);
    }

    // Delete
    public String delete(Integer id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return String.format("Registro con ID: %d eliminado",id );
        }
        return String.format("ID: %d no encontrado", id);
    }

    public Cliente buscarCorreo(String email){
        return repository.findByEmail(email);
    }
}