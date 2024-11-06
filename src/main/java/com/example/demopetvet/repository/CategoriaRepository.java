package com.example.demopetvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demopetvet.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}