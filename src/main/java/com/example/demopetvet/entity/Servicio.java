package com.example.demopetvet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "nombre", nullable = false)
    private String nombre; 

    @Column(name = "descripcion")
    private String descripcion;  

    @Column(name = "precio", nullable = false)
    private String precio; 

    @Column(name = "imagen")
    private String imagen;  

    @Column(name = "Detalles")
    private String detalles;  

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas;
}