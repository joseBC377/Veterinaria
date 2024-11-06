package com.example.demopetvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demopetvet.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

}
