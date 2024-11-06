package com.example.demopetvet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demopetvet.entity.Contactanos;
import com.example.demopetvet.repository.ContactanosRepository;
@Service
public class ContactanosService {

    @Autowired
    ContactanosRepository repository;

    public List <Contactanos> selectAll(){
        return repository.findAll();
    }
     public Contactanos selectById(Integer id) {
        return repository.findById(id).orElse(new Contactanos());
    }

    // Insert -Update
    public Contactanos insUpd(Contactanos contactanos) {
        return repository.save(contactanos);
    }

    // Delete
    public String delete(Integer id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return String.format("Registro con ID: %d eliminado",id );
        }
        return String.format("ID: %d no encontrado", id);
    }
}
