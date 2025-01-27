package com.example.demopetvet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demopetvet.entity.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByEmail(String email); 
    boolean existsByEmail(String email);
    List<Cliente> findByRol(String rol);
}
