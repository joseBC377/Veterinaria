package com.example.demopetvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demopetvet.entity.Contactanos;
import com.example.demopetvet.service.ContactanosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;



@RestController
@CrossOrigin
@RequestMapping(path = "api/contactanos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactanosRestController {

    @Autowired
    ContactanosService service;
    @GetMapping()
    public List<Contactanos> contactanosSel() {
        return service.selectAll();
    }
    
    @GetMapping("/{id}")
    public Contactanos contactanosGet(@PathVariable Integer id) {
        return service.selectById(id);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Contactanos contactanosIns(@RequestBody Contactanos contactanos) {
        return service.insUpd(contactanos);
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Contactanos contactanosupd(@RequestBody Contactanos contactanos) {
        return service.insUpd(contactanos);
    }
    @DeleteMapping(path = "/{id}")
    public String contactanosDel(@PathVariable Integer id){
        return service.delete(id);
    }
    
}
