package com.example.demopetvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demopetvet.entity.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> { 
}
