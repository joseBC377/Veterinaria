package com.example.demopetvet.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Producto")
@Data
@NoArgsConstructor
public class Producto {
    @Id
    @Column(name = "ProductoID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank(message = "La imagen es obligatoria")
    @Column(name = "Imagen")
    String imagen;

    @NotBlank(message = "El título es obligatorio")
    @Column(name = "Titulo")
    String titulo;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio debe ser un valor positivo")
    @Column(name = "Precio")
    BigDecimal precio;

    @NotNull(message = "La categoría es obligatoria")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoriaID", referencedColumnName = "CategoriaID")
    @ToString.Exclude // Excluye el campo para evitar recursión
    Categoria categoria;

    @Transient // No se almacena en la base de datos
    private String precioFormateado;

    public String getPrecioFormateado() {
        return precioFormateado;
    }

    public void setPrecioFormateado(String precioFormateado) {
        this.precioFormateado = precioFormateado;
    }

}