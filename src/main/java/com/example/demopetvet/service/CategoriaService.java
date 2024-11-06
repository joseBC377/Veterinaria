package com.example.demopetvet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demopetvet.entity.Categoria;
import com.example.demopetvet.repository.CategoriaRepository;

@Service
public class CategoriaService {
 @Autowired
    CategoriaRepository repository;

    public List<Categoria> categoriaSel(){
        return repository.findAll();
    }
    
    public Categoria categoriaGet(Integer id){
        return repository.findById(id).orElse(new Categoria());
    }

    // Crear o actualizar una categoría
    public Categoria categoriaInsUpd(Categoria categoria){
        return repository.save(categoria);
    }

    // Eliminar una categoría
    public String categoriaDel(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Categoría eliminada";
        }
        return "Categoría no encontrada";
    }
}
