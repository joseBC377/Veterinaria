package com.example.demopetvet.entity;

// import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Producto")
@Data
@NoArgsConstructor
public class Producto {
    @Id
    @Column(name ="ProductoID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "Imagen") 
    String imagen;
    @Column(name = "Titulo")
    String titulo;

    @Column(name = "Precio")
    String precio;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoriaID", referencedColumnName = "CategoriaID")
    Categoria categoria;
}