package com.example.demopetvet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demopetvet.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoriaId(Integer categoriaId);
}