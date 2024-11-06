package com.example.demopetvet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClienteID")
    Integer id;

    @Column(name = "Nombre") 
    String nombre;

    @Column(name = "Apellido") 
    String apellido;

     @Column(name = "Email", unique = true) 
     String email; 

    @Column(name = "Telefono") 
    String telefono;

    @Column(name = "Direccion")
    String direccion;

    @Column(name = "Contrasena") 
    String contrasena; 
}
