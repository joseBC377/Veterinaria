package com.example.demopetvet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demopetvet.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
     List<Reserva> findByClienteId(Integer ClienteID);
     List<Reserva> findByServicioId(Integer ServicioID);
}