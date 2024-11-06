package com.example.demopetvet.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria_producto")
@Data
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoriaID")
    private Integer id;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Producto> productos;
    
}
