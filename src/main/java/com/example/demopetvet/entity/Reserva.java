package com.example.demopetvet.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reserva")
@Data
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReservaID")
    private Integer id;

    @NotNull(message = "La fecha es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    @FutureOrPresent(message = "La fecha debe ser hoy o en el futuro")
    @Column(name = "Fecha")
    private LocalDate fecha;

    @NotNull(message = "La hora es obligatoria")
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "Hora")
    private LocalTime hora; 

    @Pattern(regexp = "\\D*", message = "No puede contener números")
    @Size(min = 2, max = 255, message = "Ingresar un nombre entre 2 y 150 caracteres")
    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "Veterinario")
    private String veterinario;
    
    @NotNull(message = "El servicio es obligatorio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ServicioID", referencedColumnName = "ServicioID")
    private Servicio servicio;
    
    @NotNull(message = "El cliente es obligatorio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ClienteID", referencedColumnName = "ClienteID")
    private Cliente cliente;
}
