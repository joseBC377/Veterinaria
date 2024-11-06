package com.example.demopetvet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demopetvet.entity.Reserva;
import com.example.demopetvet.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    // Select * from
    public List<Reserva> selectAll() {
        return repository.findAll(); 
    }

    // Seleccionar reserva por ID
    public Reserva selectById(Integer id) {
        return repository.findById(id).orElse(new Reserva()); 
    }

    // INSERT - UPDATE
    public Reserva insUpd(Reserva reserva) {
        return repository.save(reserva);
    }

    // DELETE
        public String delete(Integer id) {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return String.format("Producto con ID: %d eliminado", id);
            }
            return String.format("ID: %d no encontrado", id);
        }


}
