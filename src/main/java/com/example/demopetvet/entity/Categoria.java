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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "categoria_producto")
@Data
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoriaID")
    private Integer id;

    @NotBlank(message = "El nombre de la categoria es obligatorio")
    @Pattern(regexp = "\\D*", message = "No puede contener números")
    @Size(min= 2, max = 150, message = "Ingresar un nombre entre 2 y 100 caracteres")
    @Column(name = "Nombre")
    private String nombre;

    @NotBlank(message = "El tipo de mascota es obligatorio")
    @Pattern(regexp = "\\D*", message = "No puede contener números")
    @Size(min= 2, max = 50, message = "Ingresar un tipo de mascota entre 2 y 50 caracteres")
    @Column(name = "Tipo")
    private String tipo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria", fetch = FetchType.LAZY)
    @ToString.Exclude // Excluye el campo para evitar recursión
    private List<Producto> productos;
    
}
