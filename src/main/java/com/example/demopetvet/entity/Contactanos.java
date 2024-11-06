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
@Table(name = "contactanos")
@Data
@NoArgsConstructor
public class Contactanos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContactoID" )
    Integer id;	
    @Column(name ="Nombre" )
    String nombre;	
    @Column(name = "Apellido")
    String apellido;
    @Column(name = "Correo")	
    String correo;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ServicioID", referencedColumnName = "ServicioID")
    Servicio servicio;

    @Column(name = "Mensaje")	
    String mensaje;
}
