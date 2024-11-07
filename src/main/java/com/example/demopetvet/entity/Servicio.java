package com.example.demopetvet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "servicio")
@Data
@NoArgsConstructor
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServicioID")
    private Integer id;

    @Pattern(regexp = "\\D*", message = "No puede contener números")
    @Size(min = 2, max = 255, message = "Ingresar un nombre entre 2 y 150 caracteres")
    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 2, max = 255, message = "Ingresar un descripción entre 2 y 150 caracteres")
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio debe ser un valor positivo")
    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    @NotBlank(message = "La imagen es obligatoria")
    @Column(name = "imagen")
    private String imagen;

    @NotBlank(message = "Los detalles son obligatorios")
    @Size(min = 2, max = 255, message = "Ingresar los detalles entre 2 y 150 caracteres")
    @Column(name = "Detalles")
    private String detalles;

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas;

    @Transient // No se almacena en la base de datos
    private String precioFormateado;

    public String getPrecioFormateado() {
        return precioFormateado;
    }

    public void setPrecioFormateado(String precioFormateado) {
        this.precioFormateado = precioFormateado;
    }
}