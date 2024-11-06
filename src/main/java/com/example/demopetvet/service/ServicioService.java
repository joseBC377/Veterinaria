package com.example.demopetvet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demopetvet.entity.Servicio;
import com.example.demopetvet.repository.ServicioRepository;

@Service
public class ServicioService {
    @Autowired
    private ServicioRepository repository;

    // Método para seleccionar todos los servicios y registrar el tamaño
    public List<Servicio> selectAllWithSizeLog() {
        List<Servicio> servicios = repository.findAll(); 
        System.out.println("Servicios encontrados: " + servicios.size());
        return servicios; 
    }

    // Método para seleccionar todos los servicios
    public List<Servicio> selectAll() {
        return repository.findAll(); 
    }

    // Seleccionar servicio por ID
    public Servicio selectById(Integer id) {
        return repository.findById(id).orElse(new Servicio());
            }
  
    // INSERTAR o ACTUALIZAR un servicio
    public Servicio insUpd(Servicio servicio) {
        return repository.save(servicio);
    }

    // Eliminar un servicio
    public String delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return String.format("Servicio con ID: %d eliminado", id);
        }
        return String.format("ID: %d no encontrado", id);
    }

    // Guardar un servicio (método opcional)
    public void guardar(Servicio servicio) {
        repository.save(servicio); 
    }
}
