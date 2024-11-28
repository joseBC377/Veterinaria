package com.example.demopetvet.entity;

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
@Table(name = "reserva")
@Data
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReservaID")
    private Integer id;

    @Column(name = "Fecha")
    private String fecha;

    @Column(name = "Hora")
    private String hora; 

    @Column(name = "Veterinario")
    private String veterinario;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ServicioID", referencedColumnName = "ServicioID")
    private Servicio servicio;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ClienteID", referencedColumnName = "ClienteID")
    private Cliente cliente;
}
