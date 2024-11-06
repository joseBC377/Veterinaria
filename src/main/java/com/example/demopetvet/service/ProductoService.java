package com.example.demopetvet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demopetvet.entity.Producto;
import com.example.demopetvet.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository repository;

    // Select * from
    public List<Producto> selectAll() {
        return repository.findAll();
    }

    // Select * from WHERE
    public Producto selectById(Integer id) {
        return repository
                .findById(id)
                .orElse(new Producto());
    }

    // // Obtener productos por categoría
    // public List<Producto> obtenerProductosPorCategoria(Integer categoriaId) {
    //     return repository.findByCategoriaId(categoriaId);
    // }
    
    // INSERT - UPDATE
    public Producto insUpd(Producto producto) {
        return repository.save(producto);
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
